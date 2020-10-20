#include <stdio.h> 
#include <math.h>

double biseccion(double, double); //Prototipo de la función biseccion.

int main(void)
{
	const double x1=0.5, x2=1, x3=-2, x4=0; //Intervalos de bisección.
	short opcion;
	
	do
	{
		system("cls"); //Limpiar pantalla.
		setbuf(stdin,NULL); //Limpiar el buffer en caso que usuario ingrese un caracter no numérico.
		
		printf("\n%40s M%ctodo de bisecci%cn","",130,162);
		
		printf("\n\n\n%47s X    Y","");
		printf("\n%42s 1. [%0.2lf, %.0lf]","",x1,x2);
		printf("\n\n%42s 2. [%.1lf, %.0lf]","",x3,x4);
		printf("\n\n\n%42s 0. salir.","");
	
		printf("\n\n\n%36s Escoge un intervalo: ","");
		scanf("%hd",&opcion);	
		
		if(opcion == 1)
			biseccion(x1,x2);
		
		else if(opcion == 2)
			biseccion(x3,x4);
			
		else if(!opcion)
			printf("\n\n%44s Gracias.","");

	}while(opcion);
	
	return 0;
}

double biseccion(double x1, double x2)
{
	const double e = 2.718281828459; //Se defile variable e con el valor de euler.
	double xl = x1, xu = x2, xr = 0, producto = 0, errorAprox = 100;
	double xNuevo = 0, xAnterior = 0;
	int i = 0, j;
	
	system("cls");
	
	printf("\n\t\t\t\t\t        x    2");
	printf("\n\t\t\t\t\t%c(x) = e - 3x\n",159);
	for(j=0; j<21; j++)
		printf("%c%c%c%c",(!j ? '\t': 95), 95, 95, 95);
		
	printf("\n\tIteraci%cn\tXl\t\tXu\t\tXr\t\tError Aproximado(%%)\n",162);
	
	for(j=0; j<21; j++)
		printf("%c%c%c%c",(!j ? '\t': 196), 196, 196, 196);
		
	while((int)j > 5)
	{
		printf("\n\n\t%d \t\t%lf \t%lf \t%lf",i, xl, xu, xr);//Muestra los datos.

		//Verificar que el producto de ambos límites, sea menor que 0.
		producto = (pow(e, xl) - 3*xl*xl) * (pow(e, xu) - 3*xu*xu);
	
		if(producto >= 0) //Verificar que se cumpla la condición de signos.
			break;	//De lo contrario, salir del bucle;
			
		xr = (xl + xu) / 2; //Encontrar el promedio de Xl & Xu.
		
		producto = (pow(e, xl) - 3*xl*xl) * (pow(e, xr) - 3*xr*xr); //Producto entre Xl & Xr.
	
		producto < 0 ? (xu = xr) : (xl = xr); //Asigna el nuevo límite.

		xAnterior = xNuevo; //Asignará el valor anterior de xNuevo después de la segunda iteración.
		xNuevo = xr; //Asignar el nuevo valor de xR a xNuevo.
		
		if(i && xNuevo) //A partir de la segunda iteración, se obtendrá el error apróximado.
			errorAprox = ((xNuevo-xAnterior) / xNuevo) * 100;
			
		if(i++) //Muestra el error apróximado a partir de la segunda iteración.
			printf("\t%lf",errorAprox < 0 ? errorAprox*-1 : errorAprox);
			
		j = fabs(errorAprox) * 10; //Se almacena en la variable "j" el producto del error por 10
			//debido a que los operadores de comparación sólo evaluan números enteros.
	}
	
	getchar(); //Pausar.
	getchar();	
}
