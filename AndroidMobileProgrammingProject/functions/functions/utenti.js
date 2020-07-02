// The Firebase Admin SDK to access the Firebase Realtime Database.
const admin = require('firebase-admin');
const functions = require('firebase-functions');
let db = admin.firestore();

// Prende i dati dell'utente dall'autenticazione console di firebase
let getFirebaseUserByUID = (uid) => {
    return admin.auth()
        .getUser(uid)
        .then(function(userRecord) {
            return( userRecord );
        });
}

// Prende i dati dell'utente nel db firebase.
let getUserDataByUID = (uid) => {

    return db
        .collection('users')
        .doc(uid)
        .get()
        .then((snapshot) => {
            return snapshot.data()
        });
}

exports.getUser = functions.https.onCall((data, context) => {
    return getUserDataByUID(context.auth.uid);
});

exports.isUserInitialized = functions.https.onCall((data, context) => {

    const uid = context.auth.uid;

    // L'utente non è autenticato a Firebase
    if( uid == undefined || uid == null) {
        return false;
    }

    return getUserDataByUID(uid)
        .then(function(userRecord) {
            // console.log(userRecord);
              if( userRecord == null )
              {
                  // L'utente è autenticato a Firebase, ma NON ha un account nel database Firebase
                  // Non è presente una riga nel documento /users con l'id uguale a "uid"
                  return false;
              }else{
                  // L'utente è autenticato a Firebase, e HA un account nel database Firebase
                  // restituisco il reale valore di inizializzazione.
                  return userRecord.isUserInitialized || false;
              }
        })
        .catch((err) => {
            console.log('Error getting documents', err);
        });
});

//
exports.inserisciAffittuario = functions.https.onCall((data, context) => {
    //  https://stackoverflow.com/questions/51292378/how-do-you-insert-a-reference-value-into-firestore
    const uid = context.auth.uid;

        // Utente del DB
        return getUserDataByUID(uid)
            .then( (utenteDb) => {
                let nome = "";
                let cognome = "";

                if( utenteDb != undefined && utenteDb != null) {
                    nome = utenteDb.nome;
                    cognome = utenteDb.cognome;
                }

                return getFirebaseUserByUID(uid)
                    .then( (utente) => {
                        if( nome == null || cognome == null || nome == "" || cognome == "") {
                            let fullName = utente.displayName;
                            if(fullName != undefined) {
                                let indexSpace = fullName.indexOf(' ');
                                if( nome == null || nome == "")
                                    nome = fullName.substr(0, indexSpace);

                                if( cognome == null || cognome == "")
                                    cognome = fullName.substr(indexSpace);
                            }
                        }

                        // Settato sia nome, che cognome

                        let data = {
                            isUserInitialized: true,
                            tipo: "affittuario",
                            nome: nome,
                            cognome: cognome,
                            tornei: [],
                        };

                        return db
                            .collection('users')
                            .doc(uid)
                            .set(data, { merge: true })
                            .then( () => {
                                return true;
                            })
                            .catch((error) => {
                                console.log("ERROR!");
                                console.log(error);
                                return false;
                            });
                    });
            });


});

//
exports.inserisciProprietario = functions.https.onCall((data, context) => {
    const uid = context.auth.uid;

    // Utente del DB
    return getUserDataByUID(uid)
        .then( (utenteDb) => {
            let nome = "";
            let cognome = "";

            if( utenteDb != undefined && utenteDb != null) {
                nome = utenteDb.nome;
                cognome = utenteDb.cognome;
            }

            return getFirebaseUserByUID(uid)
                .then( (utente) => {
                    if( nome == null || cognome == null || nome == "" || cognome == "") {
                        let fullName = utente.displayName;
                        if(fullName != undefined) {
                            let indexSpace = fullName.indexOf(' ');
                            if( nome == null || nome == "")
                                nome = fullName.substr(0, indexSpace);

                            if( cognome == null || cognome == "")
                                cognome = fullName.substr(indexSpace);
                        }
                    }

                    // Settato sia nome, che cognome

                    let data = {
                        isUserInitialized: true,
                        tipo: "proprietario",
                        nome: nome,
                        cognome: cognome,
                        tornei: [],
                    };

                    return db
                        .collection('users')
                        .doc(uid)
                        .set(data, { merge: true })
                        .then( () => {
                            return true;
                        })
                        .catch((error) => {
                            console.log("ERROR!");
                            console.log(error);
                            return false;
                        });
                });
        });

});

// Va chiamata dopo del sign in con email e password
exports.registraUtente = functions.https.onCall((data, context) => {

        const uid = context.auth.uid;
        let nome = data.nome;
        let cognome = data.cognome;

       let dataUtente = {
           nome: nome,
           cognome: cognome,
       };

       return db
           .collection('users')
           .doc(uid)
           .set(dataUtente, { merge: true })
           .then( () => {
               return true;
           })
           .catch((error) => {
               console.log("ERROR!");
               console.log(error);
               return false;
           });

});

exports.disiscrizione = functions.https.onCall((data, context) => {

        const uid = context.auth.uid;

        let uidRef = db.collection("users").doc(uid);

        return db
            .collection('case')
            .doc(data.idCasa)
            .update({
               idAffittuari: admin.firestore.FieldValue.arrayRemove( uidRef )
            })
            .then(function() {
                return admin.auth().deleteUser(uid)
                  .then(function() {
                        return true;
                  });
            })
            .catch(function(error) {
                return false;
            });

});