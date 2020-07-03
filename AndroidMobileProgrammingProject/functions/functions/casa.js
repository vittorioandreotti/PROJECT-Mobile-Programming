// The Firebase Admin SDK to access the Firebase Realtime Database.
const admin = require('firebase-admin');
const functions = require('firebase-functions');
let db = admin.firestore();

// Prende i dati dell'utente dall'autenticazione console di firebase
let getFireBaseUserByUID = (uid) => {
    return admin.auth()
        .getUser(uid)
        .then(function(userRecord) {
            return( userRecord );
        });
}

/**
*
*/
exports.creaCasa = functions.https.onCall((data, context) => {

    const uid = context.auth.uid;
    let nome = data.nome;
    let indirizzo = data.indirizzo;

    if(
        nome == undefined || nome == null || nome == "" ||
        indirizzo == undefined || indirizzo == null || indirizzo == ""
    ) return false;


    let dataInput = {
        nome: nome,
        indirizzo: indirizzo,
        idProprietario: db.collection("users").doc(uid),
        idAffittuari: []
    }

    return db
        .collection('case')
        .add(dataInput)
        .then( (casaRef) => {

            let dataUtente = {
                casa: db.collection("case").doc(casaRef.id)
            };

            console.log(dataUtente)

            return db
                .collection('users')
                .doc(uid)
                .set(dataUtente, {merge: true})
                .then( () => {
                    return true;
                });
        })
        .catch((error) => {
            console.log("ERROR!");
            console.log(error);
            return false;
        });

});

/**
*
*/
exports.partecipaCasa = functions.https.onCall((data, context) => {

    const uid = context.auth.uid;
    const uidCasa = data.idCasa;

    if(
        uidCasa == undefined || uidCasa == null || uidCasa == ""
    ) return false;

    var casaRef = db.collection("case").doc(uidCasa);

    return casaRef.get()
      .then((casaDoc) => {
        if (casaDoc.exists) {

                let docUtente = db.collection("users").doc(uid);

                let dataUtenteCasa = {
                    casa: casaRef
                };

                return docUtente
                    .set(dataUtenteCasa, { merge: true })
                    .then( () => {

                        casaRef.update({
                                idAffittuari: admin.firestore.FieldValue.arrayUnion( docUtente )
                        });

                        return true;
                    })
                    .catch((error) => {
                        console.log("ERROR!");
                        console.log(error);
                        return false;
                    });

        } else {
            // La casa non esiste
            return false;
        }
    });

});


exports.getUtenteAndCasa = functions.https.onCall((data, context) => {
    const uid = context.auth.uid;

    var utenteRef = db.collection("users").doc(uid);

    return utenteRef
        .get()
        .then((snapshotUser) => {
            let userData = snapshotUser.data();
            console.log(snapshotUser);
            console.log(userData);
            let casaRef = userData.casa;

            return casaRef
                .get()
                .then((snapshotCasa) => {
                    let casaData = snapshotCasa.data();

                    console.log(snapshotCasa);
                    console.log(casaData)

                    return getFireBaseUserByUID(uid)
                        .then((utenteFirebase) => {

                            console.log(utenteFirebase);

                            return {
                                error: false,
                                casa: {
                                    id: snapshotCasa.id,
                                    nome: casaData.nome,
                                    indirizzo: casaData.indirizzo
                                },
                                utente: {
                                    id: snapshotUser.id,
                                    nome: userData.nome,
                                    cognome: userData.cognome,
                                    tipo: userData.tipo,
                                }
                            }


                        });

                });

        })
        .catch((error) => {
            console.log("ERROR!");
            console.log(error);
            return {
                error: true,
                errorMessage: error
            }
        });



});
