using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1
{
     public class Sensor
    {
        private double iD;
        private bool status;
        public Sensor(double id)
        {
            this.iD = id;
            this.status = false;
        }
        public bool IsStatus { get { return status; }
            set { status = value; }
        }
        public double ID
        {
            get { return iD; }
            set { iD = value; }
        }

        public void Imprimir()
        {
            Console.WriteLine("ID: " + this.iD);
            Console.WriteLine("Status " + IsStatus);
        }
    }
}
