# mod-external-reference-resolver

Copyright (C) 2018 The Open Library Foundation

This software is distributed under the terms of the Apache License, Version 2.0.
See the file ["LICENSE"](LICENSE) for more information.

## Additional information

## Docker deployment

```
docker build -t folio/mod-external-reference-resolver .
docker run -d -p 9003:9003 folio/mod-external-reference-resolver
```

### Publish docker image

```
docker login [docker repo]
docker build -t [docker repo]/folio/mod-external-reference-resolver:[version] .
docker push [docker repo]/folio/mod-external-reference-resolver:[version]
```

### Issue tracker

See project [FOLIO](https://issues.folio.org/browse/FOLIO)
at the [FOLIO issue tracker](https://dev.folio.org/guidelines/issue-tracker/).
