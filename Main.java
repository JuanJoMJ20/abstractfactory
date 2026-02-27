
interface ComputeInstance {
    void configure();
}

interface Database {
    void deploy();
}

class EC2 implements ComputeInstance {
    @Override
    public void configure() {
        System.out.println("AWS: Configurando instancia EC2...");
    }
}

class RDS implements Database {
    @Override
    public void deploy() {
        System.out.println("AWS: Desplegando base de datos RDS...");
    }
}

class AzureVM implements ComputeInstance {
    @Override
    public void configure() {
        System.out.println("Azure: Configurando Máquina Virtual...");
    }
}

class AzureSQL implements Database {
    @Override
    public void deploy() {
        System.out.println("Azure: Desplegando SQL Database...");
    }
}

interface CloudFactory {
    ComputeInstance createCompute();

    Database createDatabase();
}

class AWSFactory implements CloudFactory {
    @Override
    public ComputeInstance createCompute() {
        return new EC2();
    }

    @Override
    public Database createDatabase() {
        return new RDS();
    }
}

class AzureFactory implements CloudFactory {
    @Override
    public ComputeInstance createCompute() {
        return new AzureVM();
    }

    @Override
    public Database createDatabase() {
        return new AzureSQL();
    }
}

public class Main {
    public static void main(String[] args) {
        // Usando la fábrica de AWS
        CloudFactory factory = new AWSFactory();

        ComputeInstance myServer = factory.createCompute();
        Database myDatabase = factory.createDatabase();

        myServer.configure();
        myDatabase.deploy();

        System.out.println("\n--- Cambiando proveedor a Azure ---");
       
        factory = new AzureFactory();

        ComputeInstance myNewServer = factory.createCompute();
        Database myNewDatabase = factory.createDatabase();

        myNewServer.configure();
        myNewDatabase.deploy();
    }

}
