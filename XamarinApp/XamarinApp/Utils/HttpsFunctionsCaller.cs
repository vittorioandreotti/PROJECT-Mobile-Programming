using System;
using System.Collections.Generic;
using System.IO;
using System.Net;
using System.Threading.Tasks;
using Newtonsoft.Json;

namespace XamarinApp.Utils
{
    public class HttpsFunctionsCaller
    {
        private HttpWebRequest httpWebRequest;

        private const string FIREBASE_CLOUD_FUNCTIONS_URL = "https://us-central1-programmazione-mobile.cloudfunctions.net/";

        private string JsonRequest = "{}";

        HttpsFunctionsCaller( string CloudFunctionName )
        {
            string BearerToken = "";

            httpWebRequest = (HttpWebRequest)WebRequest.Create(FIREBASE_CLOUD_FUNCTIONS_URL + CloudFunctionName);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Headers.Set("Authorization", "Bearer " + BearerToken);
            httpWebRequest.Method = "POST";
        }

        private void SetData(Dictionary<string, object> inputData)
        {
            using (StreamWriter streamWriter = new StreamWriter(httpWebRequest.GetRequestStream()))
            {
                string JsonRichiesta = JsonConvert.SerializeObject(inputData);
                this.JsonRequest = JsonRichiesta;
            }
        }

        public Task<Dictionary<string, Object>> Call(Dictionary<string, Object> inputData)
        {
            this.SetData(inputData);
            return this.Call();
        }

        public Task<Dictionary<string, Object>> Call()
        {
            string BodyRequest = "{ \"data\": " + this.JsonRequest + " }";
            return
                (Task<Dictionary<string, Object>>) httpWebRequest
                    .GetResponseAsync()
                    .ContinueWith( this.HandleResponse );
        }

        private Dictionary<string, Object> HandleResponse(Task<WebResponse> response)
        {
            WebResponse webResponse = response.Result;

            //Dictionary<string, Object> ResponseData;

            using (var streamReader = new StreamReader(httpResponse.GetResponseStream()))
            {
                var result = streamReader.ReadToEnd();
            }

            Console.WriteLine()


            return new Dictionary<string, Object>();

            throw new NotImplementedException();

            private Dictionary<string, Object> HandleResponse(Task<Dictionary<string, Object>> response)
            {

                return;
            }

        }
    }
}
