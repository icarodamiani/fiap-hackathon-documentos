# fiap-hackathon-documentos

./mvnw clean install
docker build . -t icarodamiani/fiap-hackathon-documentos:latest
docker build . -t icarodamiani/fiap-hackathon-documentos:latest
helm upgrade --install documentos chart/documentos/. --kubeconfig ~/.kube/config