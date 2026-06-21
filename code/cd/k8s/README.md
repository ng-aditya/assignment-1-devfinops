## Rendered Kubernetes manifests (from Helm)

This folder contains a **concrete** YAML view of the final Kubernetes resources produced by the Helm chart.

- Source chart: `code/cd/helm/client-stack`
- Rendered output: `client-stack.all.yaml`

### Why this exists

Helm templates can feel abstract; this YAML makes it easier to understand the final state of:
- ConfigMaps + Secrets
- API Deployment/Service/HPA/Ingress
- Postgres StatefulSet/Service/PVC template

### How to regenerate

From the repository root:

```bash
mkdir -p code/cd/k8s

# Create Secrets outside of YAML/Helm (assignment requirement)
kubectl create secret generic client-stack-api \
  --from-literal=DB_PASSWORD='clientsapp' \
  --dry-run=client -o yaml | kubectl apply -f -

kubectl create secret generic client-stack-postgres \
  --from-literal=POSTGRES_PASSWORD='clientsapp' \
  --dry-run=client -o yaml | kubectl apply -f -

helm template client-stack code/cd/helm/client-stack --namespace default \
  --set api.image.repository=localhost/ngaditya1/ng-a1-service-api \
  --set api.image.tag=1.0.0 \
  --set api.image.pullPolicy=IfNotPresent \
  --set ingress.enabled=true \
  --set ingress.className=nginx \
  --set ingress.host=client-api.local \
  --set ingress.path=/ \
  --set ingress.pathType=Prefix \
  > code/cd/k8s/client-stack.all.yaml
```

