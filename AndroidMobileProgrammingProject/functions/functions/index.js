const functions = require('firebase-functions');

//// Get a reference to the database service
//var db = firebase.database();

// The Firebase Admin SDK to access the Firebase Realtime Database.
const admin = require('firebase-admin');
admin.initializeApp();

let db = admin.firestore();

// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions


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

//// getUserInfo
//exports.getUserInfo = functions.https.onCall((data, context) => {
//
//    // Authentication / user information is automatically added to the request.
//    const uid = context.auth.uid;
//
//    return getFirebaseUserByUID( uid )
//        .then(function(userFirebase) {
//
//            if( userFirebase == null )
//            {
//                // Utente non loggato
//                return {
//                    isUserInitialized: false
//                };
//
//            }else{
//
//                // Utente loggato con l'account in Firebase
//
//                return getUserDataByUID(uid)
//                    .then(function(userRecord) {
//                          if( userRecord == null )
//                          {
//                              // Utente loggato a Firebase ma non si è registrato
//
//                              return {
//                                  userFirebase: userFirebase,
//                                  isUserInitialized: false
//                              };
//                          }else{
//
//                              // Utente loggato e inizializzato
//
//                              return {
//                                  user:         userRecord,
//                                  userFirebase: userFirebase,
//                                  isUserInitialized: userRecord.isUserInitialized || false
//                              };
//                          }
//                    })
//                    .catch((err) => {
//                        console.log('Error getting documents', err);
//                    });
//            }
//        });
//});

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

//
//let inserisciSpesaAffitto = (data, context) => {
//    //
//}
//
//let inserisciSpesaBolletta = (data, context) => {
//    //
//}
//
//let inserisciSpesaCondominio = (data, context) => {
//    //
//}
//
//let inserisciSpesaComune = (data, context) => {
//    //
//}
//
////
//exports.inserisciSpesa = functions.https.onCall((data, context) => {
//
//    switch( data.tipoSpesa )
//    {
//        case 'affitto': return inserisciSpesaAffitto(data, context);
//        case 'bolletta': return inserisciSpesaBolletta(data, context);
//        case 'condominio': return inserisciSpesaCondominio(data, context);
//        case 'comune': return inserisciSpesaComune(data, context);
//    }
//
//});
//
//
//exports.inserisciTorneo = functions.https.onCall((data, context) => {
//
//    //
//
//});
//
//exports.elencoTornei = functions.https.onCall((data, context) => {
//
//    /*
//        {
//            "tornei" : [
//                {
//                  ... // Dati torneo
//                },{
//                  ... // Dati torneo
//                },
//            ],
//        }
//    */
//
//});
//
//
///**
//*
//*/
//exports.storicoTornei = functions.https.onCall((data, context) => {
//
//    /*
//        {
//            "tornei" : [
//                {
//                  ... // Dati torneo
//                },{
//                  ... // Dati torneo
//                },
//            ],
//        }
//    */
//
//});
//
//
///**
//*
//*/
//exports.elencoSpeseAffittuario = functions.https.onCall((data, context) => {
//
//    // DA DIVIDERE PER TIPOLOGIA
//    /*
//        {
//            "affitto" : {
//                "pagate" : [
//                    {
//                      ... // Dati spesa
//                    },{
//                      ... // Dati spesa
//                    },
//                ],
//                "da pagare" : [
//                    {
//                      ... // Dati spesa
//                    },{
//                      ... // Dati spesa
//                    },
//                ],
//                "in scadenza" : [
//                    {
//                      ... // Dati spesa
//                    },{
//                      ... // Dati spesa
//                    },
//                ],
//            },
//            "bollette" : {
//                "pagate" : [
//                    {
//                      ... // Dati spesa
//                    },{
//                      ... // Dati spesa
//                    },
//                ],
//                "da pagare" : [
//                    {
//                      ... // Dati spesa
//                    },{
//                      ... // Dati spesa
//                    },
//                ],
//                "in scadenza" : [
//                    {
//                      ... // Dati spesa
//                    },{
//                      ... // Dati spesa
//                    },
//                ],
//            },
//
//        }
//    */
//
//
//});
//
///**
//*
//*/
//exports.elencoSpeseProprietario = functions.https.onCall((data, context) => {
//
//    /*
//    {
//        "affitto" : {
//            "pagate" : [
//                {
//                  ... // Dati spesa
//                },{
//                  ... // Dati spesa
//                },
//            ],
//            "da pagare" : [
//                {
//                  ... // Dati spesa
//                },{
//                  ... // Dati spesa
//                },
//            ],
//        },
//        "bollette" : {
//            "pagate" : [
//                {
//                  ... // Dati spesa
//                },{
//                  ... // Dati spesa
//                },
//            ],
//            "da pagare" : [
//                {
//                  ... // Dati spesa
//                },{
//                  ... // Dati spesa
//                },
//            ],
//        },
//
//    }
//    */
//
//});
//
//
//
//
/**
* Inserisce l'utente nel database firestore, avendo in input
* i dati e l'id dell'account presente in "Authentication"
* di Firebase Console
*/
exports.registraUtente = functions.https.onCall((data, context) => {

    /*

    */
//
//    let nome = "";
//    let cognome = "";
//
//    let fullName = userFirebase.displayName || "";
//    if( fullName == null ){
//        nome = "TestNome";
//        cognome = "TestCognome";
//    }else{
//
//        let indexSpace = fullName.indexOf(' ');
//        if( indexSpace < 0) {
//            nome = "TestNome";
//            cognome = "TestCognome";
//        }else{
//            nome = fullName.substr(0, indexSpace);
//            cognome = fullName.substr(indexSpace);
//        }
//
//    }
//
//
//
//  db
//    .collection('users')
//    .doc(uid)
//    .set({
//        isUserInitialized: false,
//        nome: nome,
//        cognome: cognome,
//    });

});
//
///**
//*
//*/
//exports.creaCasa = functions.https.onCall((data, context) => {
//
//    /*
//
//    */
//
//});




//exports.test = functions.https.onRequest((req, res) => {
//
//    // Id Firebase Console di Matteo
//    let uid = 'kfAxv0ovOvg3mLyS2xRhlbSzLxW2';
//
////    res.send(uid);
//
//     getFirebaseUserByUID( uid )
//        .then(function(userFirebase) {
//            res.send( userFirebase );
//        })
//        .catch((err) => {
//            console.log('Error getting documents', err);
//        });
//
////    getUserInfoFromDb(uid)
////        .then((userRecord) => {
////            res.send( userRecord );
////        })
////        .catch((err) => {
////            console.log('Error getting documents', err);
////        });
//});

