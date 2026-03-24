import java.util.*;

 class ParkingLotSystem {

    String spots[];

    int capacity;

    ParkingLotSystem(int size){

        capacity = size;

        spots = new String[size];
    }

    public int hash(String license){

        return Math.abs(
                license.hashCode())%capacity;
    }

    public int parkVehicle(String license){

        int index = hash(license);

        int probes = 0;

        while(spots[index]!=null){

            index = (index+1)%capacity;

            probes++;
        }

        spots[index]=license;

        System.out.println(
                license+" parked at "+index+
                        " probes:"+probes);

        return index;
    }

    public void exitVehicle(String license){

        for(int i=0;i<capacity;i++){

            if(license.equals(spots[i])){

                spots[i]=null;

                System.out.println(
                        license+" exited from "+i);

                return;
            }
        }

        System.out.println("Vehicle not found");
    }

    public static void main(String args[]){

        ParkingLotSystem ps =
                new ParkingLotSystem(10);

        ps.parkVehicle("ABC123");

        ps.parkVehicle("ABC124");

        ps.parkVehicle("XYZ999");

        ps.exitVehicle("ABC123");
    }
}
