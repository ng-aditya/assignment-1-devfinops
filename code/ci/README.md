## CI (Container build assets)

This folder contains convenience scripts and container build instructions for the Quarkus API.

- Source code lives in `../source/`
- Dockerfiles live in this folder (`code/ci`) so they’re kept outside the source tree.

### Native image (multi-stage, recommended)

```bash
cd code/source
podman build -f ../ci/Dockerfile -t <dockerhub-user>/ng-a1-service-api:1.0.0 .
podman push <dockerhub-user>/ng-a1-service-api:1.0.0
```

