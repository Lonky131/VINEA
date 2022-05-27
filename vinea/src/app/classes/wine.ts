
import {winery} from './winery';
import {wineCategory} from './wine-category';

export class wine {
  id: number;
  name: string;
  productionYear: number;
  alcoholPercentage: number;
  volume: number;
  price: number;
  pictureUrl: string;
  winery : winery;
  //wineCategoryDTOList : wineCategory[];
}
