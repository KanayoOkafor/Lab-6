package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShoppingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // create a session object
        HttpSession session = request.getSession();

        // get the username from the form 
        String username = request.getParameter("user");

        // get the action parameter from the servlet
        String logout = request.getParameter("action");

        // if the user selected logout
        if (logout != null) {

            // invalidate the session object
            session.invalidate();

            // load the register jsp after the user logged out successfully
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
        }
        // check if the user put a username and load the shopping list jsp later
        if (username != null) {

            // load the shopping list jsp because a username was typed
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            return;
        }

        // the first page to load when the user loads the browser
        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String action = request.getParameter("action");

        // get item list if there is one and create one if there is none
        ArrayList<String> itemList = (ArrayList<String>) session.getAttribute("itemList");

        if (itemList == null) {
            itemList = new ArrayList<>();
        }

        if (action != null) {

            if (action.equals("Register")) {

                String username = request.getParameter("user");

                if (username != null && !username.equals("")) {

                    // if action is register, save the username as a session attribute
                    session.setAttribute("username", username);

                    // load the shopping List jsp
                    getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
                    return;
                } else {
                    request.setAttribute("message", "Invalid registry.");
                    getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                    return;
                }
            }

            if (action.equals("add")) {

                // check if the item is null
                String item = request.getParameter("itemToAdd");

                // add the item to the list if it is null
                if (item != null && !item.equals("")) {
                    itemList.add(item);
                    //save the array list into a session attribute with the name of the array list
                    session.setAttribute("itemList", itemList);
                }

                // reload the jsp with the added items
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
                return;
            }

            if (action.equals("delete")) {

                String itemToDel = request.getParameter("each_item");

                if (itemToDel != null) {
                    itemList.remove(itemToDel);
                }

                session.setAttribute("items", itemList);

                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
                return;
            }
        }
    }
}
