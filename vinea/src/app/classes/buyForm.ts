export class buyForm {
  firstName : string;
  lastName : string;
  email : string;
  phoneNumber : string;
  address : string;
  IBAN : string;
  count : number;
  idCardNumber : string;
}

export class orderForm{
  variables : {
      wineId: {
          value: number;
          type: string;
      },
      firstName: {
          value: string;
          type: string;
      },
      lastName: {
          value: string;
          type: string;
      },
      email: {
          value: string;
          type: string;
      },
      phoneNumber: {
          value: string;
          type: string;
      },
      address: {
          value: string;
          type: string;
      },
      count: {
          value: number;
          type: string;
      },
      IBAN: {
          value: string;
          type: string;
      },
      idCardNumber: {
          value: string;
          type: string;
      }
  }
}
