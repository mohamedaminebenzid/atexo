L'application est basée sur l'interaction de 3 micro-services + un service-registry:
#### 1-CONFIGURATION-SERVICE

Ce micro service est en charge d'enregistrer et de charger la configuration
des critères de numérotation.
Il doit être appelé en premier lieu avant IDENTIFIER-GENERATOR-SERVICE.

La documentation du rest api est consultable via OpenAPI sur :
http://localhost:8080/swagger-ui/index.html.

Pour configurer les critères de numérotation suivants :

- Les 3 premières lettres du prénom avec un suffixe - et ordre 1

- Le 4 premières lettres du nom avec un suffixe _ et ordre 2

- la date de naissance formatée en YYYY et ordre 3

- le compteur avec la valeur initiale 10, le préfixe C et formaté sur 5 chiffres et ordre 4

Il suffit d'appeler :

```
curl -X 'PUT' \
'http://localhost:8080/v1/configurations' \
-H 'accept: application/json' \
-H 'Content-Type: application/json' \
-d '{
"counterInitialValue": 10,
"criteria": [
{
"criterionName": "FIRST_NAME",
"prefix": "",
"suffix": "-",
"criterionType": "String",
"order": 1,
"length": 3
},
{
"criterionName": "NAME",
"prefix": "",
"suffix": "_",
"criterionType": "String",
"order": 2,
"length": 4
},
{
"criterionName": "BIRTH_DATE",
"prefix": "",
"suffix": "",
"criterionType": "Date",
"order": 3
},
{
"criterionName": "COUNTER",
"prefix": "C",
"suffix": "",
"criterionType": "Number",
"order": 4,
"length": 5
}
]
}
'
```
Pour charger la configuration déja enregistrée, il suffit d'appeler:
```
curl -X 'GET' \
'http://localhost:8080/v1/configurations' \
-H 'accept: application/json'
```
la réponse sera:
```
status code = 200

{
"counterInitialValue": 10,
"criteria": [
{
"criterionName": "FIRST_NAME",
"prefix": "",
"suffix": "-",
"criterionType": "String",
"order": 6,
"length": 3
},
{
"criterionName": "NAME",
"prefix": "",
"suffix": "_",
"criterionType": "String",
"order": 2,
"length": 4
},
{
"criterionName": "BIRTH_DATE",
"prefix": "",
"suffix": "",
"criterionType": "Date",
"order": 3
},
{
"criterionName": "COUNTER",
"prefix": "C",
"suffix": "",
"criterionType": "Number",
"order": 4,
"length": 5
}
]
}
```
#### 2-COUNTER-SERVICE
Ce micro service COUNTER-SERVICE est en charge de stocker et incrementer le compteur.
Il est appelé par IDENTIFIER-GENERATOR-SERVICE pour récupérer la nouvelle valeur du
compteur incrémentée pour chaque nouvel inscrit en entrée

La documentation du rest api est consultable via OpenAPI sur :
http://localhost:8081/swagger-ui/index.html.
Pour incrementer le compteur et renvoyer sa nouvelle valeur, il suffit d'appeler:
```
curl -X 'POST' \
'http://localhost:8081/v1/counters' \
-H 'accept: application/json' \
-d ''
```
la réponse sera:
```
status code = 200
{
"value": 11
}
```
#### 3-IDENTIFIER-GENERATOR-SERVICE
Ce micro-service est en charge de générer l'identificateur 
en fonction des données de l'inscrit et selon la configuration renvoyée
par le micro-service CONFIGURATION-SERVICE.
Il doit être appelé une fois la configuration est enregistrée par
CONFIGURATION-SERVICE.
La documentation du rest api est consultable via OpenAPI sur :
http://localhost:8082/swagger-ui/index.html

Afin de générer l'identifiant de l'inscrit, il suffit d'appeler :
```
curl -X 'POST' \
'http://localhost:8082/v1/identifiers' \
-H 'accept: application/json' \
-H 'Content-Type: application/json' \
-d '{
"name": "PASSAU",
"firstName": "Marc",
"birthDate": "1974-04-24"
}'
```
Le résultat renvoyé sera:
```
status code = 200
{
"value": "MAR-PASS_1974C00011"
}
```
### Choix Techniques:
- Pour des fins de simplicité, les données de l'application sont sauvegardées sous format de fichiers json.
  Dans un contexte HA(multi instances/JVM), il faut gérer les accès concurrents aux ressources(utiliser une BD, un systeme de fichier partagé,..)
- L'API de saisie de configuration est extensible: on peut rajouter de nouveaux critères de numerotation sans casser les autres API
- Le versionning des APIs permet d'assurer une compatibilité ascendante
- Choix de PUT au lieu de POST au niveau du service de configuration vu l'idempotence du PUT


### Evolutions possibles:
- Au niveau de IDENTIFIER-GENERATOR-SERVICE, on peut utiliser un cache expirable pour stocker la configuration des critères de numérotation(chargée depuis CONFIGURATION-SERVICE)
  afin d'améliorer la performance.
- Rajouter plus de TU
- Rajouter un API Gateway(pas nécessaire vu la simplicité de l'architecture actuelle)
- Rajouter une couche sécurité
- Rajouter plus de résilience aux APIs (circuit breaker, retry,..)
- Error handling (ajout de business exception, retourner les bons status code)
