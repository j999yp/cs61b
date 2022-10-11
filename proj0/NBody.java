public class NBody {

    public static double readRadius(String path)
    {
        In file = new In(path);
        file.readInt();
        return file.readDouble();
    }

    public static Planet[] readPlanets(String path){
        In file = new In(path);
        int n = file.readInt();
        Planet[] planets = new Planet[n];
        file.readDouble(); //radius
        for(int i=0;i<n;i+=1){
            double xPos = file.readDouble();
            double yPos = file.readDouble();
            double xVel = file.readDouble();
            double yVel = file.readDouble();
            double mass = file.readDouble();
            String img = file.readString();
            planets[i] = new Planet(xPos,yPos,xVel,yVel,mass,img);
        }
        return planets;
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius,radius);
        
        double time=0;
        while(time <= T){
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for(int i = 0; i < planets.length; i+=1){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for(int i = 0; i < xForces.length; i+=1){
                planets[i].update(dt,xForces[i],yForces[i]);
            }
            StdDraw.picture(0,0, "images/starfield.jpg", radius*2, radius*2);
            for(int i=0;i<planets.length;i+=1){
                planets[i].draw();
            }
            StdDraw.show();
            time+=dt;
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
    }
}
