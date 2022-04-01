import { DatePipe } from "@angular/common";
import { setupTestingRouter } from "@angular/router/testing";

export class Patient {

   patient_id ? : number;

    firstname ?: string;

    lastname ?: string;

    email ?: string;

    password  ?: string;

    phone  ? : number;

    sex ?: string;

   birthdate ?: Date;
   //birthdate ?: string;

    address ?: string;

    city ?: string;

    state ?: string;

    zip_code ?: number;
    //date_created ?: string;

    date_created  ? : Date;

    /*
    constructor(){
        super();
    };
    */

    constructor( patient_id: number, firstname: string, lastname: string, email: string,
        password: string, phone : number, sex : string, birthdate :string,
        address: string, city: string, state :string, zip_code: number, 
        date_created: Date ) {
            
         }
    

    /*
    constructor(public patient_id: number, public firstname: string, public lastname: string, public email: string,
        public password: string, public  phone : number, public sex : string, public birthdate :string,
        public address: string, public city: string, public state :string, public zip_code: number, 
        public date_created: Date  ) {
            
         }
         */

}
