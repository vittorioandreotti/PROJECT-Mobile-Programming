// The Firebase Admin SDK to access the Firebase Realtime Database.
const admin = require('firebase-admin');
const functions = require('firebase-functions');
let db = admin.firestore();

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
        idProprietario: uid,
        idAffittuari: []
    }

    return db
        .collection('case')
        .add(dataInput)
        .then( () => {
            return true;
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
            // La casa esiste
            // Atomically add a new region to the "regions" array field.
                casaRef.update({
                    idAffittuari: firebase.firestore.FieldValue.arrayUnion(uid)
                });
                return true;
        } else {
            // La casa non esiste
            return false;
        }
    });

});
