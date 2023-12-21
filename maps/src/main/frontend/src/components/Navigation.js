import { Outlet, Link } from "react-router-dom";
import "../styles/Navigation.css"

export default function Navigation() {
  return (
    <>
      <nav>
        <ul>
          <li>
            <Link to="/home">Home</Link> 
          </li>
         
          <li>
            <Link to="/maps">Maps</Link>
          </li>
          <li>
            <Link to="/courses">Courses</Link>
          </li>
        </ul>
      </nav>

      <Outlet />
    </>
  )
};