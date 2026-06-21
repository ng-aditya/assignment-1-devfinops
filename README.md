## DevFinOps Assignment (Kubernetes + DevOps + FinOps)

### Repo layout

- `code/source`: Quarkus REST API (Java, virtual threads) + Flyway migrations
- `code/ci`: container build instructions (Podman/Docker)
- `code/cd`: Kubernetes delivery assets (Helm chart/K8s manifests + kind ingress notes)

### API behavior

- `GET /api/service/clients` returns a JSON array of client records persisted in Postgres.
- Schema + seed data are created by **Flyway** on service startup.

### Kubernetes/Helm (one-command deploy)

Chart: `code/cd/helm/client-stack`

Rendered YAML (for clarity): `code/cd/k8s/client-stack.all.yaml`

1) Build & push API image (update repo/tag in Helm `values.yaml`):

See `code/ci/README.md`.

2) Create kind cluster and install ingress-nginx:

See `code/cd/README.md`.

3) Install:

```bash
helm upgrade --install client-stack ./code/cd/helm/client-stack
kubectl get all,ingress,cm,secret,pvc
```

4) Call API:

```bash
curl -H 'Host: client-api.local' http://localhost:9090/api/service/clients
```

### Demo checklist (for screen recording)

- Show all objects running: `kubectl get all,ingress,hpa,pvc`
- API call: `curl -H 'Host: client-api.local' http://localhost:9090/api/service/clients`
- Self-healing: delete one API pod and show it recreates:
  - `kubectl delete pod -l app.kubernetes.io/component=api`
- DB recovery + persistence: delete DB pod and show data remains:
  - `kubectl delete pod -l app.kubernetes.io/component=postgres`
  - re-run the API curl
- Rolling update: update `.Values.api.image.tag` and `helm upgrade ...` then:
  - `kubectl rollout status deploy/client-stack-api`
- HPA: generate load and show scale (requires metrics-server):
  - `kubectl get hpa -w`

### FinOps notes (what to say/write)

- **Requests/limits** are set for API pods (see Helm `values.yaml`) to enable bin-packing and avoid noisy-neighbor issues.
- **HPA** scales API pods based on CPU utilization (cost scales with demand).
- **Cost optimization opportunities (examples)**:
  - Right-size requests/limits using observed `kubectl top pods` metrics.
  - Use HPA + (if available) cluster autoscaler to reduce idle capacity.
  - Use multi-stage native build + minimal runtime image to reduce runtime footprint.

