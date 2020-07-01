// The Firebase Admin SDK to access the Firebase Realtime Database.
const admin = require('firebase-admin');
const functions = require('firebase-functions');
let db = admin.firestore();


exports.inserisciTorneo = functions.https.onCall((data, context) => {


            let titolo = data.titolo;
            let indirizzo = data.indirizzo;
            let dataOraEvento = admin.firestore.Timestamp.fromDate( new Date( data.dataOraEvento ) );
            let categoria = data.categoria;
            let regolamento = data.regolamento || "";


            if(
                titolo == undefined || titolo == null || titolo == "" ||
                dataOraEvento == undefined || dataOraEvento == null ||
                categoria == undefined || categoria == null || categoria == "" ||
                indirizzo == undefined || indirizzo == null || indirizzo == ""
            ) return false;

            let dataInput = {
                titolo: titolo,
                indirizzo: indirizzo,
                regolamento: regolamento,
                categoria: categoria,
                dataOraEvento: dataOraEvento,
                partecipanti: []
            }
            return db
                .collection('tornei')
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

// exports.partecipaTorneo = functions.https.onCall((data, context) => {
//
//     const uid = context.auth.uid;
//     const uidTorneo = data.idTorneo;
//
//     if(
//         uidTorneo == undefined || uidTorneo == null || uidTorneo == ""
//     ) return false;
//
//     var TorneoRef = db.collection("tornei").doc(uidTorneo);
//
//     return TorneoRef.get()
//       .then((torneoDoc) => {
//         if (torneoDoc.exists) {
//
//                 let docUtente = db.collection("users").doc(uid);
//
//                 let dataUtenteTorneo = {
//                     torneo: torneoRef
//                 };
//
//                 return docUtente
//                     .set(dataUtenteTorneo, { merge: true })
//                     .then( () => {
//
//                         torneoRef.update({
//                                 partecipanti: admin.firestore.FieldValue.arrayUnion( docUtente )
//                         });
//
//                         return true;
//                     })
//                     .catch((error) => {
//                         console.log("ERROR!");
//                         console.log(error);
//                         return false;
//                     });
//
//         } else {
//             // Il toreno non esiste
//             return false;
//         }
//     });
//
// });



exports.elencoTornei = functions.https.onCall((data, context) => {

    /*
        {
            "tornei" : [
                {
                  ... // Dati torneo
                },{
                  ... // Dati torneo
                },
            ],
        }
    */

});


/**
*
*/
exports.storicoTornei = functions.https.onCall((data, context) => {

    /*
        {
            "tornei" : [
                {
                  ... // Dati torneo
                },{
                  ... // Dati torneo
                },
            ],
        }
    */

});