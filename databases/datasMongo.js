db.createCollection("discussion");
db.createCollection("historique");
d.createCollection("favoris");

db.discussion.insertMany([
    {
        membres : [
            { 
                idProfil: "PRF0001", 
                nom: "INSSA",
                prenom: "Chalman",
                dateNaissance : new Date(2002,06,19),
                email : "chalmanInssa1962002@gmail.com",
                mdp : "chalman",
                status : 1,
                contact : "0345091434",
                role : "Client",
                username : "chalman"
            },
            { 
                idProfil: "PRF0002", 
                nom: "Fy",
                prenom: "Michael",
                dateNaissance : new Date(2005,08,10),
                email : "fyMichael@gmail.com",
                mdp : "fy",
                status : 1,
                contact : "0341111111",
                role : "Client",
                username : "fy",
            }
        ],
        messages : [
            {
                dateCreation : new Date(2024,01,23) ,
                emetteur : "PRF0001",
                contenu : "Salut toi",
                status : 1
            },
            {
                dateCreation : new Date(2024,01,23) ,
                emetteur : "PRF0002",
                contenu : "Salut",
                status : 1
            },
            {
                dateCreation : new Date(2024,01,23) ,
                emetteur : "PRF0001",
                contenu : "ca va",
                status : 1
            }
        ],
        dateCreation : new Date(2024, 01, 22),
        status : 1
    },
]);

db.historique.insertMany([
    {
        idProfil : "PRF0002",
        description : "Vous avez vendu la voiture Berline RENAULT 208 a Rakoto Jean",
        idAnnonce : "ANN0001",
        dateCreation : new Date(2024, 01, 25)
    }
]);

db.favoris.insertMany([
    {
        idProfil :"Prod002",
        idAnnnonce : "ANN001",
        dareCreation : new Date(2024, 01, 25)
    }
]);