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
        file.readDouble();
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

}
