import { EgretLoadable } from "egret";
import { useTranslation, withTranslation, Trans } from 'react-i18next';
import ConstantList from "../../appConfig";
const EgretCalendar = EgretLoadable({
  loader: () => import("./EgretCalendar")
});
const ViewComponent = withTranslation()(EgretCalendar);
const settings = { 
  footer: { show: false }
};
const calendarRoutes = [
  {
    path:  ConstantList.ROOT_PATH+"calendar",
    exact: true,
    component: ViewComponent,
    settings
  }
];

export default calendarRoutes;
