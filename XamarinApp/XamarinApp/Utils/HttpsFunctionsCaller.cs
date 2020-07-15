using System;
using System.Collections.Generic;
using System.IO;
using System.Net;
using System.Runtime.CompilerServices;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;
using XamarinApp.Models.Helpers;

namespace XamarinApp.Utils
{
    public class HttpsFunctionsCaller
    {
        private HttpWebRequest httpWebRequest;

        private const string FIREBASE_CLOUD_FUNCTIONS_URL = "https://us-central1-programmazione-mobile.cloudfunctions.net/";

        private string JsonRequest = "{}";

        public HttpsFunctionsCaller( string CloudFunctionName )
        {
            UtentePreferences utentePreferences = new UtentePreferences();

            string BearerToken = utentePreferences.GetAuthToken();

            httpWebRequest = (HttpWebRequest)WebRequest.Create(FIREBASE_CLOUD_FUNCTIONS_URL + CloudFunctionName);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Headers.Set("Authorization", "Bearer " + BearerToken);
            httpWebRequest.Method = "POST";
        }

        private void SetData(Dictionary<string, object> inputData)
        {
            string JsonRichiesta = JsonConvert.SerializeObject(inputData);
            this.JsonRequest = JsonRichiesta;
        }

        public Task<CloudFunctionResponse> Call(Dictionary<string, Object> inputData)
        {
            this.SetData(inputData);
            return this.Call();
        }

        public Task<CloudFunctionResponse> Call()
        {
            string BodyRequest = "{ \"data\": " + this.JsonRequest + " }";

            this.httpWebRequest.ContentLength = BodyRequest.Length;
            this.httpWebRequest.Expect = "application/json";

            this.httpWebRequest.GetRequestStream().Write(Encoding.UTF8.GetBytes(BodyRequest), 0, BodyRequest.Length);

            return httpWebRequest
                    .GetResponseAsync()
                    .ContinueWith( this.HandleResponse );
        }

        private CloudFunctionResponse HandleResponse(Task<WebResponse> response)
        {
            TaskAwaiter<WebResponse> taskAwaiter = response.GetAwaiter();

            WebResponse webResponse = taskAwaiter.GetResult();

            using (var streamReader = new StreamReader(webResponse.GetResponseStream()))
            {
                var StringResult = streamReader.ReadToEnd();

                Dictionary<string, object> DictionaryResponse = JsonConvert.DeserializeObject<Dictionary<string, object>>(StringResult);

                return new CloudFunctionResponse(DictionaryResponse);
            }
        }
    }
}
