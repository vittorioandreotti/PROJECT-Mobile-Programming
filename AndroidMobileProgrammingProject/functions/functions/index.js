const functions = require('firebase-functions');

//// Get a reference to the database service
//var db = firebase.database();

// The Firebase Admin SDK to access the Firebase Realtime Database.
const admin = require('firebase-admin');
admin.initializeApp();

let db = admin.firestore();

// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//
// exports.helloWorld = functions.https.onRequest((request, response) => {
//  response.send("Hello from Firebase!");
// });


let getFirebaseUserByUID = (uid) => {
    return admin.auth()
        .getUser(uid)
        .then(function(userRecord) {
            return( userRecord );
        });
}

let getUserDataByUID = (uid) => {

    return db
        .collection('users')
        .doc(uid)
        .get()
        .then((snapshot) => {
            return snapshot.data()
        });

}

// getUserInfo
exports.getUserInfo = functions.https.onCall((data, context) => {

    // Authentication / user information is automatically added to the request.
    const uid = context.auth.uid;

    return getFirebaseUserByUID( uid )
        .then(function(userFirebase) {

            if( userFirebase == null )
            {
                // Utente non loggato
                return {
                    isUserInitialized: false
                };

            }else{

                // Utente loggato

                return getUserDataByUID(uid)
                    .then(function(userRecord) {
                          if( userRecord == null )
                          {
                              // Utente loggato ma non inizializzato

                              return {
                                  userFirebase: userFirebase,
                                  isUserInitialized: false
                              };
                          }else{

                              // Utente loggato e forse inizializzato

                              return {
                                  user:         userRecord,
                                  userFirebase: userFirebase,
                                  isUserInitialized: userRecord.isUserInitialized || false
                              };
                          }
                    })
                    .catch((err) => {
                        console.log('Error getting documents', err);
                    });
            }
        });
});

//exports.test = functions.https.onRequest((req, res) => {
//
//    let uid = 'PrNrobCbObgoBnspqZMA5PiHva82';
//
//    getUserInfoFromDb(uid)
//        .then((userRecord) => {
//            res.send( userRecord );
//        })
//        .catch((err) => {
//            console.log('Error getting documents', err);
//        });
//});

