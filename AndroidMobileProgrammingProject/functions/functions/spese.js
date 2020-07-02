// The Firebase Admin SDK to access the Firebase Realtime Database.
const admin = require('firebase-admin');
const functions = require('firebase-functions');
let db = admin.firestore();



let inserisciSpesaBolletta = (data, context) => {
                let dataInserimento = data.dataInserimento;
                let dataScadenza = data.dataScadenza;
                let spesa_totale = data.prezzo;
                let categoria = data.categoria;
                let idCasa = data.idCasa;

                // Get a new write batch
                const batch = db.batch();

                if(
                    idCasa == undefined || idCasa == null || idCasa == "" ||
                    categoria == undefined || categoria == null || categoria == "" ||
                    dataInserimento == undefined || dataInserimento == null ||
                    dataScadenza == undefined || dataScadenza == null ||
                    spesa_totale == undefined || spesa_totale == null
                ) return false;

                let dataInput = {
                    prezzoTotale: spesa_totale,
                    categoria: categoria,
                    tipo: "bolletta",
                    dataInserimento: dataInserimento,
                    dataScadenza: dataScadenza,

                }
                return db
                    .collection('case')
                    .doc(idCasa)
                    .collection("spese")
                    .add(dataInput)
                    .then( ( spesaSnapshot ) => {

                        return db
                            .collection("case")
                            .doc(idCasa)
                            .get()
                            .then(( datiCasaSnapshot )=>{
                                let datiCasa = datiCasaSnapshot.data();

                                let spesa_singola = spesa_totale / datiCasa.idAffittuari.length;

                                let spesaUtentiCollectionRef = db
                                   .collection('case')
                                   .doc(idCasa)
                                   .collection("spese")
                                   .doc(spesaSnapshot.id)
                                   .collection("utenti");

                                for( let i = 0; i < datiCasa.idAffittuari.length; i++) {

                                    let docPathAffittuario = datiCasa.idAffittuari[i].path;
                                    let indexBarra = docPathAffittuario.lastIndexOf("/")
                                    let idUtenteSingolo = "";
                                    if(indexBarra >= 0) {
                                        idUtenteSingolo = docPathAffittuario.substr(indexBarra+1);
                                    }else{
                                        idUtenteSingolo = docPathAffittuario;
                                    }

                                    let docNuovaSpesaUtenteRef = spesaUtentiCollectionRef.doc(idUtenteSingolo);
                                    let datiSpesaUtente = {
                                        dataPagamento : null,
                                        prezzo : spesa_singola,
                                    };

                                    batch.set(docNuovaSpesaUtenteRef, datiSpesaUtente);
                                }

                                // Commit the batch
                                return batch.commit().then(()=>{
                                    return true;
                                });



                            });
                    })
                    .catch((error) => {
                        console.log("ERROR!");
                        console.log(error);
                        return false;
                    });
}

let inserisciSpesaAffitto = (data, context) => {
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

