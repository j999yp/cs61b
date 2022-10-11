public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double dx = xxPos - p.xxPos;
        double dy = yyPos - p.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Planet p) {
        double dis = calcDistance(p);
        return G * mass * p.mass / dis / dis;
    }
    public double calcForceExertedByX(Planet p) {
        double force = calcForceExertedBy(p);
        double dx = p.xxPos - xxPos;
        return force / calcDistance(p) * dx;
    }

    public double calcForceExertedByY(Planet p) {
        double force = calcForceExertedBy(p);
        double dy = p.yyPos - yyPos;
        return force / calcDistance(p) * dy;
    }

    public double calcNetForceExertedByX(Planet[] array){
        double sum=0;
        for(int i=0;i<array.length;i+=1){
            if(!this.equals(array[i])){
                sum+=calcForceExertedByX(array[i]);
            }
        }
        return sum;
    }

    public double calcNetForceExertedByY(Planet[] array){
        double sum=0;
        for(int i=0;i<array.length;i+=1){
            if(!this.equals(array[i])){
                sum+=calcForceExertedByY(array[i]);
            }
        }
        return sum;
    }

    public void update(double time, double fx, double fy){
        double ax = fx / mass;
        double ay = fy / mass;
        xxVel += ax * time;
        yyVel += ay * time;
        xxPos += xxVel * time;
        yyPos += yyVel * time;
    }

    public void draw(){
        StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
    }
}