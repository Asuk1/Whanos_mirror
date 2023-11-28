# Utilisation de l'image de base
FROM gcc:13.2 as builder

# Répertoire de travail
WORKDIR /app

# Copie du contenu du répertoire local dans le conteneur
COPY . .

# Compilation avec make
RUN make

# Image légère pour exécution
FROM debian:bullseye-slim

# Copie du binaire compilé depuis l'étape précédente
# changer le path
COPY --from=builder /app/votre_programme /usr/local/bin/votre_programme

# Commande par défaut pour l'exécution du binaire
# il faut changer le path de l'exec
CMD ["/usr/local/bin/votre_programme"]