// The Firebase Admin SDK to access the Firebase Realtime Database.
const admin = require('firebase-admin');
const functions = require('firebase-functions');
let db = admin.firestore();

exports.inserisciTorneo = functions.https.onCall((data, context) => {

    //

});

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