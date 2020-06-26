// The Firebase Admin SDK to access the Firebase Realtime Database.
const admin = require('firebase-admin');
const functions = require('firebase-functions');
let db = admin.firestore();



let inserisciSpesaAffitto = (data, context) => {
    //
}

let inserisciSpesaBolletta = (data, context) => {
    //
}

let inserisciSpesaCondominio = (data, context) => {
    //
}

let inserisciSpesaComune = (data, context) => {
    //
}

//
exports.inserisciSpesa = functions.https.onCall((data, context) => {

    switch( data.tipoSpesa )
    {
        case 'affitto': return inserisciSpesaAffitto(data, context);
        case 'bolletta': return inserisciSpesaBolletta(data, context);
        case 'condominio': return inserisciSpesaCondominio(data, context);
        case 'comune': return inserisciSpesaComune(data, context);
    }

});


/**
*
*/
exports.elencoSpeseAffittuario = functions.https.onCall((data, context) => {

    // DA DIVIDERE PER TIPOLOGIA
    /*
        {
            "affitto" : {
                "pagate" : [
                    {
                      ... // Dati spesa
                    },{
                      ... // Dati spesa
                    },
                ],
                "da pagare" : [
                    {
                      ... // Dati spesa
                    },{
                      ... // Dati spesa
                    },
                ],
                "in scadenza" : [
                    {
                      ... // Dati spesa
                    },{
                      ... // Dati spesa
                    },
                ],
            },
            "bollette" : {
                "pagate" : [
                    {
                      ... // Dati spesa
                    },{
                      ... // Dati spesa
                    },
                ],
                "da pagare" : [
                    {
                      ... // Dati spesa
                    },{
                      ... // Dati spesa
                    },
                ],
                "in scadenza" : [
                    {
                      ... // Dati spesa
                    },{
                      ... // Dati spesa
                    },
                ],
            },

        }
    */


});

/**
*
*/
exports.elencoSpeseProprietario = functions.https.onCall((data, context) => {

    /*
    {
        "affitto" : {
            "pagate" : [
                {
                  ... // Dati spesa
                },{
                  ... // Dati spesa
                },
            ],
            "da pagare" : [
                {
                  ... // Dati spesa
                },{
                  ... // Dati spesa
                },
            ],
        },
        "bollette" : {
            "pagate" : [
                {
                  ... // Dati spesa
                },{
                  ... // Dati spesa
                },
            ],
            "da pagare" : [
                {
                  ... // Dati spesa
                },{
                  ... // Dati spesa
                },
            ],
        },

    }
    */

});

