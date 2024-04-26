# CONFIGURATION-SERVICE

Ce micro service est en charge d'enregistrer et charger la configuration
des critères de numérotation.
Il doit être appelé en premier lieu avant IDENTIFIER-GENERATOR-SERVICE.

La documentation du rest api est consultable via OpenAPI sur :
http://localhost:8080/swagger-ui/index.html
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
"orderedCriteria": [
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
curl -X 'GET' \
'http://localhost:8080/v1/configurations' \
-H 'accept: application/json'

la réponse sera:

status = 200

{
"counterInitialValue": 10,
"orderedCriteria": [
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


