# microService

Le projet est fait par : ARMAND Kee-Soon, BERRADI Ines, PETITJEAN Fanny

## Lancer le projet


```console
git clone 
```

Lancer les services suivants en même temps (pas obligatoirement):
- boutique
- hero  
- incubateuroeuf
- inventaireincubateur
- inventairemonstre
- inventaireoeuf
- monstre
- stockage

Quand tout est lancé, démarrer le service **visuel**

Ouvrir le lien, quand le service est lancé :
[http://localhost:3009/](http://localhost:3009/)

Pour vérifier la reception de nos message sur Rabbit mq : 
- Ouvrir le lien : <http://localhost:15672/#/> 
- Username : guest , Password : guest 
- Dès le début d'incubation d'un oeuf le microservice va envoyer un message au microservice boutique qui sera le receiver. 


