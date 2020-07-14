using System;
using System.Collections.Generic;
using System.Text;
using System.Text.RegularExpressions;
using System.Net.Mail;

namespace XamarinApp.Utils
{
    abstract class Helper
    {
        private static string VALID_EMAIL_ADDRESS_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

        public static Boolean IsEmailValid(String EmailStr)
        {
            try
            {
                MailAddress m = new MailAddress(EmailStr);
                return true;
            }
            catch (FormatException)
            {
                return false;
            }
        }

        public static DateTime FromStringToDate(String DateString)
        {
            return FromStringToDate(DateString, "dd/MM/yyyy");
        }

        public static DateTime FromStringToDate(String DateString, String DateFormat)
        {
            // TODO: Da convertire in c#
            /*try
            {
                SimpleDateFormat DataFormat = new SimpleDateFormat(DateFormat);
                return DataFormat.Parse(DateString);
            }
            catch (ParseException e)
            {
                e.PrintStackTrace();
                return null;
            }
            */
            return new DateTime();
        }

        public static DateTime FromStringToDateTime(String DateString, String OraString)
        {
            // return FromStringToDate(DateString + " " + OraString, "dd/MM/yyyy HH:mm");
            return new DateTime();
        }

        public static String FormatDateToStringWithHour(DateTime Data)
        {
            if (Data == null) return "-";
            // return new SimpleDateFormat("dd/MM/yyyy HH:mm").Format(Data);
            return "";
        }

        public static String FormatDateToString(DateTime Data)
        {
            if (Data == null) return "-";
            // return new SimpleDateFormat("dd/MM/yyyy").Format(Data);
            return "";
        }

        public static String GetDateFormat()
        {
            return "%02d/%02d/%04d";
        }

        public static String GetTimeFormat()
        {
            return "%02d:%02d";
        }

        public static DateTime FromMillisToDate(Int64 DataInserimento)
        {
            return (new DateTime(1970, 1, 1)).AddMilliseconds(DataInserimento);
        }


        

    }
}
