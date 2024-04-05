import { User } from "./user.model";

export class Goods {
    [x: string]: any;

    goodsId?: number | any;
    price?: any;

    master?: User | any;

    description?: string | any;

    customers?: User[] | any;

    image?: any;
    
    //NEW
    imageData?: string;

}
