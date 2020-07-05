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

                                spesa_singola = Math.ceil(spesa_singola * 100) / 100;

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
                    let dataInserimento = data.dataInserimento;
                    let dataScadenza = data.dataScadenza;
                    let spesa_totale = data.prezzo;
                    let titolo = data.titolo;
                    let idCasa = data.idCasa;

                    // Get a new write batch
                    const batch = db.batch();

                    if(
                        idCasa == undefined || idCasa == null || idCasa == "" ||
                        titolo == undefined || titolo == null || titolo == "" ||
                        dataInserimento == undefined || dataInserimento == null ||
                        dataScadenza == undefined || dataScadenza == null ||
                        spesa_totale == undefined || spesa_totale == null
                    ) return false;

                    let dataInput = {
                        titolo: titolo,
                        tipo: "affitto",
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

                                    let spesa_singola = spesa_totale;

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

let inserisciSpesaCondominio = (data, context) => {
                        let dataInserimento = data.dataInserimento;
                        let spesa_totale = data.prezzo;
                        let nome = data.nome;
                        let idCasa = data.idCasa;

                        // Get a new write batch
                        const batch = db.batch();

                        if(
                            idCasa == undefined || idCasa == null || idCasa == "" ||
                            nome == undefined || nome == null || nome == "" ||
                            dataInserimento == undefined || dataInserimento == null ||
                            spesa_totale == undefined || spesa_totale == null
                        ) return false;

                        let dataInput = {
                            nome: nome,
                            tipo: "condominio",
                            dataInserimento: dataInserimento,

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

                                        let spesa_singola = spesa_totale;

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

let inserisciSpesaComune = (data, context) => {
                        let dataInserimento = data.dataInserimento;
                        let spesa_totale = data.prezzo;
                        let nome = data.nome;
                        let descrizione = data.descrizione;
                        let idCasa = data.idCasa;

                        // Get a new write batch
                        const batch = db.batch();

                        if(
                            idCasa == undefined || idCasa == null || idCasa == "" ||
                            nome == undefined || nome == null || nome == "" ||
                            descrizione == undefined || descrizione == null || descrizione == "" ||
                            dataInserimento == undefined || dataInserimento == null ||
                            spesa_totale == undefined || spesa_totale == null
                        ) return false;

                        let dataInput = {
                            prezzoTotale: spesa_totale,
                            nome: nome,
                            descrizione: descrizione,
                            tipo: "comune",
                            dataInserimento: dataInserimento,

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

                                        spesa_singola = Math.ceil(spesa_singola * 100) / 100;

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

//
exports.inserisciSpesa = functions.https.onCall((data, context) => {

    switch( data.tipoSpesa )
    {
        case 'affitto': return inserisciSpesaAffitto(data, context);        // Non divisa tra gli affittuari
        case 'bolletta': return inserisciSpesaBolletta(data, context);      // Divisa tra gli affittuari
        case 'condominio': return inserisciSpesaCondominio(data, context);  // Non divisa tra gli affittuari
        case 'comune': return inserisciSpesaComune(data, context);          // Divisa tra gli affittuari
    }

});

async function leggiSpesePerUtente( idCasa, idSpesa, datiSpesa ) {
    try {
        return db
            .collection("case")
            .doc(idCasa)
            .collection("spese")
            .doc(idSpesa)
            .collection("utenti")
            .get()
            .then((utentiSnapshot)=>{
                // tutti gli utenti di una singola spesa

                let allDatiSpese = [];

                utentiSnapshot.forEach(function(utenteSnapshot) {
                    // Singolo utente di una singola spesa


                    let utenteData = utenteSnapshot.data();

                    let dataPagamento = utenteData.dataPagamento;

                    let objDataSpesa = {
                            idUtente:  utenteSnapshot.id,
                            idSpesa:  idSpesa,
                            nome: datiSpesa.nome,
                            descrizione: datiSpesa.descrizione,
                            dataInserimento: datiSpesa.dataInserimento,
                            dataPagamento: utenteData.dataPagamento,
                            prezzo: utenteData.prezzo,
                            tipo: datiSpesa.tipo,
                    };

                    allDatiSpese.push( objDataSpesa );
                });

                return allDatiSpese;
            });

    } catch(errorMsg) {
        console.log("ERROR!");
        console.log(errorMsg);
        return [];
    }

}

async function leggiSpesePerCasa(idCasa) {

    try {

       let oggettoRitorno = {
           sommario : { daPagare:[], pagate:[] },
           affitto : { daPagare:[], pagate:[] },
           bollette : { daPagare:[], pagate:[] },
           comune : { daPagare:[], pagate:[] },
           condominio : { daPagare:[], pagate:[] },
       };

        return db
             .collection("case")
             .doc(idCasa)
             .collection("spese")
             .get()
             .then( (speseSnapshot) => {
                     // Tutte le spese di una casa

                     let spesePerUtentePromises = [];

                     // Per ogni spesa di una casa
                     speseSnapshot.forEach((spesaRef) => {

                         let idSpesa = spesaRef.id;
                         let datiSpesa = spesaRef.data();

                         let spesePerUtentePromise = leggiSpesePerUtente( idCasa, idSpesa, datiSpesa )
                             .then((spesePerUtente)=>{
                                 console.log(spesePerUtente);
                                 for( let j = 0; j < spesePerUtente.length; j++ ){

                                     let spesaPerUtente = spesePerUtente[j];

                                     if( spesaPerUtente.dataPagamento == null ){
                                         // NON HA PAGATO
                                          oggettoRitorno[spesaPerUtente.tipo].daPagare.push(spesaPerUtente);
                                          oggettoRitorno.sommario.daPagare.push(spesaPerUtente);

                                     }else{
                                         // HA PAGATO
                                          oggettoRitorno[spesaPerUtente.tipo].pagate.push(spesaPerUtente);
                                          oggettoRitorno.sommario.pagate.push(spesaPerUtente);
                                     }

                                 };
                             });

                             spesePerUtentePromises.push(spesePerUtentePromise);

                     });

                     return Promise.all(spesePerUtentePromises).then(()=>{
                         console.log("oggettoRitorno");
                         console.log(oggettoRitorno);
                         oggettoRitorno.error = false;
                         return oggettoRitorno;
                     });


             });

    } catch(errorMsg) {
        console.log("ERROR!");
        console.log(errorMsg);
        return [];
    }
}

/**
*
*/
exports.elencoSpeseAffittuario = functions.https.onCall((data, context) => {

        let idCasa = data.idCasa;
        if(idCasa == undefined || idCasa == null || idCasa == "")
            return {
                error: true,
                errorMessage: "Nessun codice casa presente..."
            };

        return leggiSpesePerCasa(idCasa)
            .then((obj)=>{
                console.log("Returning")
                console.log(obj)
                return obj;
            })
            .catch((errorMsg) => {
                console.log("ERROR!");
                console.log(errorMsg);
                return {
                    error: true,
                    errorMessage: errorMsg
                };
            });


});


//                            for( let j = 0; j < speseSingoleUtente.length; j++ ){
//                                let spesaSingolaUtente = speseSingoleUtente[j];
//                                let datiSpesa = datiSpese[j];
//
//                                let datiSpesaSingolaUtente = spesaSingolaUtente.data();
//
//                                let objDataSpesa = {
//                                        nome: datiSpesaSingolaUtente.nome,
//                                        descrizione: datiSpesaSingolaUtente.descrizione,
//                                        dataInserimento: datiSpesaSingolaUtente.dataInserimento,
//                                        prezzo: datiUtenteSpesaSingola.prezzo
//                                };
//                                if( dataPagamento == null ){
//                                    // NON HA PAGATO
//                                     oggettoRitorno[tipoSpesaDoc].daPagare.push(objDataSpesa);
//                                     objDataSpesa.tipo = tipoSpesaDoc;
//                                     oggettoRitorno.sommario.daPagare.push(objDataSpesa);
//
//                                }else{
//                                    // HA PAGATO
//                                    objDataSpesa.dataPagamento = datiUtenteSpesaSingola.dataPagamento;
//                                    oggettoRitorno[tipoSpesaDoc].pagate.push(objDataSpesa);
//                                    objDataSpesa.tipo = tipoSpesaDoc;
//                                    oggettoRitorno.sommario.pagate.push(objDataSpesa);
//                                }
//                            }


                            // Qualcosa



//
//                  speseSnapshot.forEach((doc) => {
//                    // Per ogni spesa di una casa
//                    let datiSpesaSingola = doc.data();
//
//                    // Tipo della spesa singola
//                    let tipoSpesaDoc = datiSpesaSingola.tipo;
//
////                    let utentiSpesaSingola =
//                    let utentiSnapshot = doc.collection("utenti").get();
//
//
//                         // Tutti gli utenti di una spesa
//
//                    utentiSnapshot.forEach((utenteDoc) => {
//                            // Per ogni utente di una singola spesa
//                            let datiUtenteSpesaSingola = utenteDoc.data();
//
//                            let dataPagamento = datiUtenteSpesaSingola.dataPagamento;
//
//                            let objDataSpesa = {
//                                    nome: datiSpesaSingola.nome,
//                                    descrizione: datiSpesaSingola.descrizione,
//                                    dataInserimento: datiSpesaSingola.dataInserimento,
//                                    prezzo: datiUtenteSpesaSingola.prezzo
//                            };
//                            if( dataPagamento == null ){
//                                // NON HA PAGATO
//                                 oggettoRitorno[tipoSpesaDoc].daPagare.push(objDataSpesa);
//                                 objDataSpesa.tipo = tipoSpesaDoc;
//                                 oggettoRitorno.sommario.daPagare.push(objDataSpesa);
//
//                            }else{
//                                // HA PAGATO
//                                objDataSpesa.dataPagamento = datiUtenteSpesaSingola.dataPagamento;
//                                oggettoRitorno[tipoSpesaDoc].pagate.push(objDataSpesa);
//                                objDataSpesa.tipo = tipoSpesaDoc;
//                                oggettoRitorno.sommario.pagate.push(objDataSpesa);
//                            }
//
//                    });
//              });
//              oggettoRitorno.error = false;
//              return oggettoRitorno;



/**
*

exports.elencoSpeseProprietario = functions.https.onCall((data, context) => {

    let oggettoRitorno = {
           sommario : { nonPagate:[], pagate:[] },
           affitto : { nonPagate:[], pagate:[] },
           bollette : { nonPagate:[], pagate:[] },
           condominio : { nonPagate:[], pagate:[] },
       };

    let idCasa = data.idCasa;
    if(idCasa == undefined || idCasa == null || idCasa == "") return false;

    return db
        .collection("case")
        .doc(idCasa)
        .collection("spese")
        .get()
        .then( (speseSnapshot) => {
                // Tutte le spese di una casa

              speseSnapshot.forEach((doc) => {
                // Per ogni spesa di una casa
                let datiSpesaSingola = doc.data();

                // Tipo della spesa singola
                let tipoSpesaDoc = datiSpesaSingola.tipo;

                let utentiSnapshot = doc.collection("utenti").get();
                // Tutti gli utenti di una spesa

                        utentiSnapshot.forEach((utenteDoc) => {
                            // Per ogni utente di una singola spesa
                            let datiUtenteSpesaSingola = utenteDoc.data();

                            let dataPagamento = datiUtenteSpesaSingola.dataPagamento;

                            let objDataSpesa = {
                                    nome: datiSpesaSingola.nome,
                                    descrizione: datiSpesaSingola.descrizione,
                                    dataInserimento: datiSpesaSingola.dataInserimento,
                                    prezzo: datiUtenteSpesaSingola.prezzo
                            };
                            if( dataPagamento == null ){
                                // NON PAGATA
                                 oggettoRitorno[tipoSpesaDoc].nonPagate.push(objDataSpesa);
                                 objDataSpesa.tipo = tipoSpesaDoc;
                                 oggettoRitorno.sommario.nonPagate.push(objDataSpesa);

                            }else{
                                // PAGATA
                                objDataSpesa.dataPagamento = datiUtenteSpesaSingola.dataPagamento;
                                oggettoRitorno[tipoSpesaDoc].pagate.push(objDataSpesa);
                                objDataSpesa.tipo = tipoSpesaDoc;
                                oggettoRitorno.sommario.pagate.push(objDataSpesa);
                            }

                });
          });

          return oggettoRitorno;
    });
});

*/