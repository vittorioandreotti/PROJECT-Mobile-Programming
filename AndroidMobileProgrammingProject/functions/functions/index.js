// The Firebase Admin SDK to access the Firebase Realtime Database.
const admin = require('firebase-admin');
const functions = require('firebase-functions');

admin.initializeApp();

let db = admin.firestore();

module.exports = {
  ...require("./spese.js"),
  ...require("./tornei.js"),
  ...require("./utenti.js"),
  ...require("./casa.js"),
};

// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions






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

