# EIPA Ingestion App

App returns updated electricity charging point. Program using spring webflux and spring integration frameworks.
In application.properties file you can configure:

`export-data=address with data`
`export-data-token=user token`
`repeat-rate=time intervals between request to server`

## Updated Points

### GET

App returning json with data on api event address:

- `/api/updated-points`

