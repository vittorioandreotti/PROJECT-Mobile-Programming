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

exports.isUserInitialized = functions.https.onCall((data, context) => {

    const uid = context.auth.uid;

    // L'utente non è autenticato a Firebase
    if( uid == undefined || uid == null) {
        return false;
    }

    return getUserDataByUID(uid)
        .then(function(userRecord) {
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
    //

    // Set isUserInitialized = false

});

//
exports.inserisciProprietario = functions.https.onCall((data, context) => {
    //

    // Set isUserInitialized = false

});