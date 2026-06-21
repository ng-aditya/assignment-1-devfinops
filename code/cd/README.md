## CD (Kubernetes + Helm)

This folder contains Kubernetes delivery artifacts.

### Helm chart

The chart `helm/client-stack` deploys:
- Quarkus API (Deployment, Service, Ingress, HPA)
- Postgres (StatefulSet, Service, Secret, PVC)

### kind + ingress-nginx (Podman Desktop)

1) Create a kind cluster that exposes ports 80/443:

```bash
cat > kind-config.yaml <<'EOF'
kind: Cluster
apiVersion: kind.x-k8s.io/v1alpha4
nodes:
  - role: control-plane
    extraPortMappings:
      - containerPort: 80
        hostPort: 80
        protocol: TCP
      - containerPort: 443
        hostPort: 443
        protocol: TCP
EOF

kind create cluster --name devfinops --config kind-config.yaml
```

2) Install ingress-nginx:

```bash
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/kind/deploy.yaml
kubectl wait --namespace ingress-nginx --for=condition=available deploy/ingress-nginx-controller --timeout=180s
```

3) Install the chart (edit `values.yaml` first):

```bash
helm upgrade --install client-stack ./helm/client-stack
```

4) Add host entry for demo:

`client-api.local` -> `127.0.0.1`

Then:

```bash
curl -H 'Host: client-api.local' http://localhost:9090/api/service/clients | jq .
```

