using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Insira um numero") ;
            double a = Convert.ToDouble(Console.ReadLine());
            Sensor sensor = new Sensor(a);
            sensor.Imprimir();
            sensor.IsStatus = true;
            sensor.Imprimir();
        }
    }
}
