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

 exports.partecipaTorneo = functions.https.onCall((data, context) => {

     const uid = context.auth.uid;
     const uidTorneo = data.idTorneo;

     if(
         uidTorneo == undefined || uidTorneo == null || uidTorneo == ""
     ) return false;

     var torneoRef = db.collection("tornei").doc(uidTorneo);

     return torneoRef.get()
       .then((torneoDoc) => {
         if (torneoDoc.exists) {

                 let docUtente = db.collection("users").doc(uid);

                  return docUtente.update({
                     tornei: admin.firestore.FieldValue.arrayUnion( torneoRef )
                  })
                  .then( () => {

                         torneoRef.update({
                                 partecipanti: admin.firestore.FieldValue.arrayUnion( docUtente )
                         });

                         return true;
                     })
                     .catch((error) => {
                         console.log("ERROR!");
                         console.log(error);
                         return false;
                     });

         } else {
             // Il torneo non esiste
             return false;
         }
     });

 });



exports.elencoTornei = functions.https.onCall((data, context) => {

    let oggettoRitorno = {
        tornei : [],
   };

   const uid = context.auth.uid;

    return db
        .collection("tornei")
        .get()
        .then( (torneiSnapshot) => {
                // Tutti i tornei

              torneiSnapshot.forEach((doc) => {
                // Per ogni torneo
                let datiTorneo = doc.data();


                    let dataOraEvento = datiTorneo.dataOraEvento || null;
                    if( dataOraEvento != null ) {
                        dataOraEvento = dataOraEvento.toMillis();
                    }

                    let objDataTorneo = {
                            id: doc.id,
                            titolo: datiTorneo.titolo,
                            dataOra: dataOraEvento,
                            indirizzo: datiTorneo.indirizzo,
                            categoria: datiTorneo.categoria,
                            regolamento: datiTorneo.regolamento,
                    };

                     oggettoRitorno["tornei"].push(objDataTorneo)

              });

            oggettoRitorno.error = false;
            return oggettoRitorno;
        })
        .catch((error) => {
            console.log("ERROR!");
            console.log(error);
            return {
                error: true,
                tornei : [],
            };
        });

});



exports.storicoTornei = functions.https.onCall((data, context) => {
    let oggettoRitorno = {
        tornei : [],
   };

   const uid = context.auth.uid;

    return db
        .collection("users")
        .doc(uid)
        .get()
        .then( (utenteSnapshot) => {
                // Tutti i tornei

                let dataUtenteSnapshot = utenteSnapshot.data();
                let torneiRefArray = dataUtenteSnapshot.tornei;

                let promises = [];

                for( let i = 0; i < torneiRefArray.length; i++) {
                    let torneoRef = torneiRefArray[i];

                    let torneoDoc = torneoRef.get();
                    promises.push(torneoDoc)
                }

                return Promise.all(promises)
                .then((snapshotTornei)=>{

                    for( let i = 0; i < snapshotTornei.length; i++) {
                        let torneoSnapshotCorrente = snapshotTornei[i];

                        let datiTorneo = torneoSnapshotCorrente.data();

                        let dataOraEvento = datiTorneo.dataOraEvento || null;
                        if( dataOraEvento != null ) {
                            dataOraEvento = dataOraEvento.toMillis();
                        }

                        let objDataTorneo = {
                                id: torneoSnapshotCorrente.id,
                                titolo: datiTorneo.titolo,
                                dataOra: dataOraEvento,
                                indirizzo: datiTorneo.indirizzo,
                                categoria: datiTorneo.categoria,
                                regolamento: datiTorneo.regolamento,
                        };

                        console.log(objDataTorneo);
                         oggettoRitorno.tornei.push(objDataTorneo);
                    };


                    oggettoRitorno.error = false;
                    return oggettoRitorno;
                });

        })
        .catch((error) => {
            console.log("ERROR!");
            console.log(error);
            return {
                error: true,
                tornei : [],
            };
        });

});