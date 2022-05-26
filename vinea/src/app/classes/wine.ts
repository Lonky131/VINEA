
import {winery} from './winery';

export class wine {
  id: number;
  name: string;
  productionYear: number;
  alcoholPercentage: number;
  volume: number;
  price: number;
  pictureUrl: string;
  winery : winery;
}
