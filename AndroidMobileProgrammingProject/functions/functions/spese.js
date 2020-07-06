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

                // gg/mm/aaaa
                let dataInserimentoSplitted = null;
                if(dataInserimento != null) {
                    dataInserimentoSplitted = dataInserimento.split("/");
                }

                // gg/mm/aaaa
                let dataScadenzaSplitted = null;
                if(dataScadenza != null) {
                    dataScadenzaSplitted = dataScadenza.split("/");
                }

                let dataInput = {
                    prezzoTotale: spesa_totale,
                    categoria: categoria,
                    tipo: "bolletta",
                    dataInserimento: dataInserimento != null ? new Date(dataInserimentoSplitted[2], dataInserimentoSplitted[1], dataInserimentoSplitted[0]) : null,
                    dataScadenza: dataScadenza != null ? new Date(dataScadenzaSplitted[2], dataScadenzaSplitted[1], dataScadenzaSplitted[0]) : null,
                }

                console.log(dataInput);

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

                    // gg/mm/aaaa
                    let dataInserimentoSplitted = null;
                    if(dataInserimento != null) {
                        dataInserimentoSplitted = dataInserimento.split("/");
                    }

                    // gg/mm/aaaa
                    let dataScadenzaSplitted = null;
                    if(dataScadenza != null) {
                        dataScadenzaSplitted = dataScadenza.split("/");
                    }

                    let dataInput = {
                        titolo: titolo,
                        tipo: "affitto",
                        dataInserimento: dataInserimento != null ? new Date(dataInserimentoSplitted[2], dataInserimentoSplitted[1], dataInserimentoSplitted[0]) : null,
                        dataScadenza: dataScadenza != null ? new Date(dataScadenzaSplitted[2], dataScadenzaSplitted[1], dataScadenzaSplitted[0]) : null,
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

                        // gg/mm/aaaa
                        let dataInserimentoSplitted = null;
                        if(dataInserimento != null) {
                            dataInserimentoSplitted = dataInserimento.split("/");
                        }


                        let dataInput = {
                            nome: nome,
                            tipo: "condominio",
                            dataInserimento: dataInserimento != null ? new Date(dataInserimentoSplitted[2], dataInserimentoSplitted[1], dataInserimentoSplitted[0]) : null,
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

                        // gg/mm/aaaa
                        let dataInserimentoSplitted = null;
                        if(dataInserimento != null) {
                            dataInserimentoSplitted = dataInserimento.split("/");
                        }

                        let dataInput = {
                            prezzoTotale: spesa_totale,
                            nome: nome,
                            descrizione: descrizione,
                            tipo: "comune",
                            dataInserimento: dataInserimento != null ? new Date(dataInserimentoSplitted[2], dataInserimentoSplitted[1], dataInserimentoSplitted[0]) : null,
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

                                            let dataPagamentoTmp = null;
                                            if(idUtenteSingolo == context.auth.uid) {
                                                dataPagamentoTmp = admin.firestore.Timestamp.now();
                                            }

                                            let datiSpesaUtente = {
                                                dataPagamento : dataPagamentoTmp,
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

                    let dataInserimentoMod = datiSpesa.dataInserimento || null;
                    if(dataInserimentoMod != null) dataInserimentoMod = dataInserimentoMod.toMillis();

                    let dataPagamentoMod = dataPagamento || null;
                    if(dataPagamentoMod != null) dataPagamentoMod = dataPagamentoMod.toMillis();

                    let dataScadenzaMod = datiSpesa.dataScadenza || null;
                    if(dataScadenzaMod != null) dataScadenzaMod = dataScadenzaMod.toMillis();

                    let objDataSpesa = {
                            idUtente:  utenteSnapshot.id,
                            idSpesa:  idSpesa,
                            nome: datiSpesa.nome || "",
                            titolo: datiSpesa.titolo || "",
                            descrizione: datiSpesa.descrizione || "",
                            dataInserimento: dataInserimentoMod,
                            dataPagamento: dataPagamentoMod,
                            dataScadenza: dataScadenzaMod,
                            categoria: datiSpesa.categoria || "",
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
           bolletta : { daPagare:[], pagate:[] },
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
//                         console.log();
                         let idSpesa = spesaRef.id;
                         let datiSpesa = spesaRef.data();

                         let spesePerUtentePromise = leggiSpesePerUtente( idCasa, idSpesa, datiSpesa )
                             .then((spesePerUtente)=>{
//                                 console.log(spesePerUtente);
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
        return {
            error: true,
            errorMessage: errorMsg
        };
    }
}

async function aggiungiDatiUtentePerSpesa(objSpese) {

    let datiUtenti = {};

    let tipiSpese = Object.keys(objSpese);
    for( let i = 0; i < tipiSpese.length; i++ ) {
        // comune, affitto
        let tipoSpesa = tipiSpese[i];

        let tipiPagati = Object.keys(objSpese[tipoSpesa]);
        for( let j = 0; j < tipiPagati.length; j++ ) {
            // daPagare e pagate
            let tipoPagato = tipiPagati[j];
            for( let k = 0; k < objSpese[tipoSpesa][tipoPagato].length; k++ ) {
                let spesaSingola = objSpese[tipoSpesa][tipoPagato][k];
                datiUtenti[spesaSingola.idUtente] = {};
            }
        }
    }

//    console.log(datiUtenti);

    let promiseUtenti = [];

    let chiaviChiavi = Object.keys(datiUtenti);
    for(let i = 0; i < chiaviChiavi.length; i++ ) {
        let chiaveUtente = chiaviChiavi[i];

        let promiseUtente = db
            .collection("users")
            .doc(chiaveUtente)
            .get()
            .then((utenteSnapshot)=>{
                let utenteData = utenteSnapshot.data();
                let idUtente = utenteSnapshot.id;
                let nomeUtente = utenteData.nome;
                let cognomeUtente = utenteData.cognome;

                datiUtenti[idUtente] = {
                    nome: nomeUtente,
                    cognome: cognomeUtente,
                };

//                console.log(datiUtenti[idUtente]);

            });

        promiseUtenti.push(promiseUtente);
    }

    return Promise.all(promiseUtenti).then(()=>{

        let tipiSpese = Object.keys(objSpese);
        for( let i = 0; i < tipiSpese.length; i++ ) {
            // comune, affitto
            let tipoSpesa = tipiSpese[i];

            let tipiPagati = Object.keys(objSpese[tipoSpesa]);
            for( let j = 0; j < tipiPagati.length; j++ ) {
                // daPagare e pagate
                let tipoPagato = tipiPagati[j];


                for( let k = 0; k < objSpese[tipoSpesa][tipoPagato].length; k++ ) {
                    // spesa singola
                    let datiSpesaSingola = objSpese[tipoSpesa][tipoPagato][k];
                    let datiUser = datiUtenti[datiSpesaSingola.idUtente];

                    objSpese[tipoSpesa][tipoPagato][k].nomeUtente = datiUser.nome;
                    objSpese[tipoSpesa][tipoPagato][k].cognomeUtente = datiUser.cognome;
                    console.log(datiSpesaSingola);

                }
            }
        }

        return objSpese;
    });
}

/**
*
*/
exports.elencoSpese = functions.https.onCall((data, context) => {

        let idCasa = data.idCasa;
        if(idCasa == undefined || idCasa == null || idCasa == "")
            return {
                error: true,
                errorMessage: "Nessun codice casa presente..."
            };

        return leggiSpesePerCasa(idCasa)
            .then((objSpese)=>{
                return aggiungiDatiUtentePerSpesa(objSpese);
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


exports.pagaSpesa = functions.https.onCall ((data, context) => {
    const uid = context.auth.uid;
    let idCasa = data.idCasa;
    let idSpesa = data.idSpesa;

    if(
        idCasa == undefined || idCasa == null || idCasa == "" ||
        idSpesa == undefined || idSpesa == null || idSpesa == ""
    ) return false;

   let spesaRef = db
        .collection('case')
        .doc(idCasa)
        .collection("spese")
        .doc(idSpesa)
        .collection('utenti')
        .doc(uid);

        return spesaRef.update({
                dataPagamento: admin.firestore.FieldValue.serverTimestamp()
        })
        .then (() => {
            return true;
        })
        .catch((error) => {
            console.log("ERROR!");
            console.log(error);
            return false;
        });
});