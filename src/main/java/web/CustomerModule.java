/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web;

import dao.CustomerDAO;
import dao.DaoExceptionMapper;
import domain.Customer;
import helpers.ErrorMessage;
import io.jooby.Jooby;
import io.jooby.StatusCode;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.exception.ConstraintsViolatedException;

/**
 *
 * @author Lachy Kitchen
 */
public class CustomerModule extends Jooby {

    public CustomerModule(CustomerDAO dao) {

        get("/api/customers/{username}", ctx -> {

            String username = ctx.path("username").value();

            Customer customer = dao.searchByUsername(username);
            
            // set sensitive customer data to null to prevent it being stored in the session storage
            customer.setEmailAddress(null);
            customer.setShippingAddress(null);
            customer.setSurname(null);
            customer.setPassword(null);

            customer.setPassword(null);
            if (customer == null) {
                // no customer with that ID found, so return a 404/Not Found error
                return ctx.send(StatusCode.NOT_FOUND);
            } else {
                return customer;
            }
        });

        post("/api/register/", ctx -> {            
            
            try { 
                Customer customer = ctx.body().to(Customer.class);
                
                // check that the customer objects that get created meet the constraints.
                new Validator().assertValid(customer);

                // save the customer
                dao.saveCustomer(customer);

                // redirect the browser back to the sign in page
                //response.sendRedirect("sign-in.html");
            
            } catch (ConstraintsViolatedException ex) {

                ErrorMessage err = new ErrorMessage();
                
                // get the violated constraints from the exception
                ConstraintViolation[] violations = ex.getConstraintViolations();
                
                for (ConstraintViolation violation : violations) {
                    err.addError(violation.getMessage());   
                }

                //create erorr message passing in constraints violates
                //set response code 
                return ctx.setResponseCode(StatusCode.CONFLICT_CODE).render(err);
            } catch (Exception ex) {
                
                ErrorMessage err = new ErrorMessage();
                
                DaoExceptionMapper exception = new DaoExceptionMapper();

                err.addError(exception.messageFromException(ex));   
                
                return ctx.setResponseCode(StatusCode.CONFLICT_CODE).render(err);
            }

            return ctx.send(StatusCode.CREATED);
        });
    }

}
