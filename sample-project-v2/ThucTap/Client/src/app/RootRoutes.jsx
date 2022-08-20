import React from "react";
import { Redirect } from "react-router-dom";
import homeRoutes from "./views/home/HomeRoutes";
import sessionRoutes from "./views/sessions/SessionRoutes";
import dashboardRoutes from "./views/dashboard/DashboardRoutes";
import administrativeUnitRoutes from "./views/AdministrativeUnit/AdministrativeUnitRoutes"; 
import UserRoutes from "./views/User/UserRoutes"; 
import ConstantList from "./appConfig";
// import MenuRoutes from "./views/Menus/MenuRoutes";
import pageLayoutRoutes from "./views/page-layouts/PageLayoutRoutees";  
import CategoryRoutes from "./views/Category/CategoryRoutes";  
import ProjectRoutes from "./views/Project/ProjectRoutes"; 
import CalendarRoutes from "./views/calendar/CalendarRoutes";  
import JobRoutes from "./views/Job/JobRoutes";   
import MenuRoutes from "./views/Menus/MenuRoutes"; 
import CustomerRoutes from "./views/Customer/CustomerRoutes"

import EventRoutes from "./views/Event/EventRoutes"; 

// const redirectRoute = [
//   {
//     path: ConstantList.ROOT_PATH,
//     exact: true,
//     component: () => <Redirect to={ConstantList.HOME_PAGE} /> //Luôn trỏ về HomePage được khai báo trong appConfig
//   }
// ];

const errorRoute = [
  {
    component: () => <Redirect to={ConstantList.ROOT_PATH + "session/404"} />
  }
];

const routes = [ 
  ...homeRoutes,
  ...sessionRoutes,
  ...dashboardRoutes,
  ...CalendarRoutes,
  ...administrativeUnitRoutes, 
  ...pageLayoutRoutes,  
  ...CategoryRoutes,  
  ...MenuRoutes,
  ...UserRoutes, 
  ...CustomerRoutes, 
  ...EventRoutes, 
  ...ProjectRoutes, 
  ...errorRoute

];

export default routes;
