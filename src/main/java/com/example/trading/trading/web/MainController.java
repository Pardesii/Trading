package com.example.trading.trading.web;
import com.example.trading.trading.model.Ownership;
import com.example.trading.trading.model.Stock;
import com.example.trading.trading.model.Transaction;
import com.example.trading.trading.model.User;
import com.example.trading.trading.service.*;
import com.example.trading.trading.utils.RandomNumberGenerator;
import com.example.trading.trading.web.dto.HoldingDto;
import com.example.trading.trading.web.dto.TransactionDto;
import com.example.trading.trading.web.dto.WalletDto;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
// TODO: Separating controllers and pushing out get user from every api.
@Controller
public class MainController {

    private StockService stockService;
    private UserService userService;
    private FavoriteService favoriteService;
    private OwnershipService ownershipService;
    private TransactionService transactionService;


    public MainController(StockService stockService, UserService userService, FavoriteService favoriteService, OwnershipService ownershipService, TransactionService transactionService) {
        this.stockService = stockService;
        this.userService = userService;
        this.favoriteService = favoriteService;
        this.ownershipService = ownershipService;
        this.transactionService = transactionService;
    }

    @GetMapping("/api/favRem/{id}")
    public String removeFromFav(@PathVariable Long id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        long userId = userService.getAUserByEmail(username).getId();
        favoriteService.removeFromFavorite(id, userId);


        return "redirect:/stocks";
    }

    @GetMapping("/api/favAdd/{id}")
    public String addToFav(@PathVariable Long id) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        long userId = userService.getAUserByEmail(username).getId();

        favoriteService.addToFavorite(id, userId);

        return "redirect:/stocks";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/stocks")
    public String stocks(Model model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        long userId = userService.getAUserByEmail(username).getId();
        User user = userService.getAUserByEmail(username);

        model.addAttribute("curUser", user);
        model.addAttribute("favstocks", stockService.getAllStocksFavorite(userId));
        model.addAttribute("nonstocks", stockService.getAllStocksNonFavorite(userId));

        return "stocks";
    }

    @GetMapping("/stocks/specific")
    public String stockByCat(Model model, @RequestParam("cat") String cat) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        long userId = userService.getAUserByEmail(username).getId();

        System.out.println(cat);
        model.addAttribute("favstocks", stockService.getAllStocksByCategoryFav(cat, userId));
        model.addAttribute("nonstocks", stockService.getAllStocksByCategoryNonFav(cat, userId));
        return "stocks";
    }

    @GetMapping("/bla")
    public String bla() {
        return "bla";
    }

    @GetMapping("/portfolio")
    public String portfolio(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        User myUser = userService.getAUserByEmail(username);
        System.out.println("-------------------------"+userService.getAUserByEmail(username)+" "+username);

        // calculate present value of stock

        List<Ownership> allHeld = ownershipService.getAllHeldStocks(myUser.getId());
        Long prValue = 0l;
        for(Ownership o: allHeld) {
            Long quant = o.getQuantity();
            Long pricePerStock = stockService.getPerStockPrice(o.getStockId());
            prValue += (quant * pricePerStock);
        }

        List<HoldingDto> hd = new ArrayList<>();
//        HoldingDto hdto = new HoldingDto();
        for(Ownership o: allHeld) {
            HoldingDto hdto = new HoldingDto();
            hdto.setStockName(stockService.getStockNameFromStockId(o.getStockId()));
            hdto.setInvestedValue(o.getCostPrice());
            long val = 0;
            Long pricePerStock = stockService.getPerStockPrice(o.getStockId());
            val = pricePerStock * o.getQuantity();
            hdto.setPresentValue(val);
            double diff = ( (double)val - (double)o.getCostPrice())/100;

            NumberFormat formatter = new DecimalFormat("#0.00");
            double newDiff = Double.parseDouble(formatter.format(diff));
            hdto.setDiff(newDiff);
            hd.add(hdto);

        }

        double diff = ( (double)prValue - (double)myUser.getInvestedValue())/100;

        NumberFormat formatter = new DecimalFormat("#0.00");
        double newDiff = Double.parseDouble(formatter.format(diff));

        model.addAttribute("holdingData", hd);
        model.addAttribute("diff", newDiff);
        model.addAttribute("totalValue", prValue);
        model.addAttribute("users", userService.getAUserByEmail(username));
        return "portfolio";
    }

    @GetMapping("/wallet")
    public String wallet(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        System.out.println("-------------------------"+userService.getAUserByEmail(username)+" "+username);
        model.addAttribute("user", userService.getAUserByEmail(username));
        return "wallet";
    }


    @ModelAttribute("wallet")
    public WalletDto walletDto() {
        return new WalletDto();
    }

    @PostMapping("/api/wallet/add")
    public String addToWallet(@ModelAttribute("wallet") WalletDto walletDto) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        if(walletDto.getAmount() <= 0) {
            return "redirect:/wallet?failure";
        }

        User myUser = userService.getAUserByEmail(username);

        Long prev = myUser.getWallet();

        prev += walletDto.getAmount();

        userService.modifyWallet(myUser.getId(), prev);



        return "redirect:/wallet?success";
    }

    @PostMapping("/api/wallet/withdraw")
    public String removeFromWallet(@ModelAttribute("wallet") WalletDto walletDto) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        User myUser = userService.getAUserByEmail(username);

        if(walletDto.getAmount() > myUser.getWallet() || walletDto.getAmount() < 0) {
            return "redirect:/wallet?failure";
        }


        Long prev = myUser.getWallet();

        prev -= walletDto.getAmount();

        userService.modifyWallet(myUser.getId(), prev);



        return "redirect:/wallet?success";
    }


    @GetMapping("/orders/sort")
    public String findPaginated(
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        User myUser = userService.getAUserByEmail(username);
        int pageSize = 100;

        Page < Transaction > page = transactionService.findPaginated(1, pageSize, sortField, sortDir);
        List < Transaction > listOrders = page.getContent();

        for(Transaction tr: listOrders) {
            if(tr.getUserId() != myUser.getId()) {
                tr.setUserId(0l);
            }
        }

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("orders", listOrders);
        model.addAttribute("curUser", myUser.getId());
        return "ordersV3";
    }

    @GetMapping("/orders")
    public String orderHistory(Model model) {
        return findPaginated( "id", "asc", model);
    }

    @GetMapping("/admin/stock-refresher")
    public String refreshStocks() {
        // dynamic stocks logic
        List<Stock> allStocks = stockService.getAllStocks();
        for(Stock myStock: allStocks) {
            myStock.setPrevPrice(myStock.getCurPrice());
            myStock.setCurPrice(RandomNumberGenerator.generate(myStock.getStartValue(), myStock.getEndValue()));
            myStock.setDiff(myStock.getCurPrice() - myStock.getPrevPrice());
            stockService.saveStock(myStock);
        }
        return "redirect:/stocks";
    }



    @GetMapping("/transaction")
    public String transaction() {
        return "transaction";
    }


    @ModelAttribute("trans")
    public TransactionDto transactionDto() {
        return new TransactionDto();
    }

    @PostMapping("/api/transaction")
    public String saveTransaction(@ModelAttribute("trans") TransactionDto transactionDto) {

        System.out.println(transactionDto.getQuantity()+"--------------------------------------------------------------------");

        LocalDateTime localDateTime;

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        User myUser = userService.getAUserByEmail(username);

        Long pricePerQuantity = stockService.getPerStockPrice(transactionDto.getStockId());
        Long totalPrice = pricePerQuantity * transactionDto.getQuantity();

        if(transactionDto.getOrderType().equals("Buy")) {

            if(myUser.getWallet() < totalPrice) {
                return "redirect:/transaction?fail";
            }
            else {
                // modification in user table

                //change wallet
                Long prev = myUser.getWallet();
                prev -= totalPrice;

                //update wallet
                myUser.setWallet(prev);

                //update invested value
                Long prevInvestedValue = myUser.getInvestedValue();
                prevInvestedValue += totalPrice;
                myUser.setInvestedValue(prevInvestedValue);

                //save the user data
                userService.updateNewUser(myUser);

                // modification in ownership table
                int newOwner = 0;
                Ownership ownership = ownershipService.getOwnership(transactionDto.getStockId(), myUser.getId());

                // create new ownership
                if(ownership == null) {
                    newOwner += 1;

                    Ownership newOwnershipObject = new Ownership();
                    newOwnershipObject.setUserId(myUser.getId());
                    newOwnershipObject.setStockId(transactionDto.getStockId());
                    newOwnershipObject.setQuantity(transactionDto.getQuantity());
                    newOwnershipObject.setCostPrice(totalPrice);
                    ownershipService.saveOwnership(newOwnershipObject);
                }
                else {
                    // update ownership
                    Long prevCostPrice = ownership.getCostPrice();
                    Long prevQuantity = ownership.getQuantity();
                    ownership.setCostPrice(prevCostPrice+totalPrice);
                    ownership.setQuantity(prevQuantity + transactionDto.getQuantity());
                    ownershipService.saveOwnership(ownership);
                }


                // change in stock table

                Stock myStock = stockService.getIndividualStock(transactionDto.getStockId());

                if(newOwner == 1) {
                    long prevOwners = myStock.getInvestedUsers();
                    myStock.setInvestedUsers(prevOwners + newOwner);
                }

                Long prevAum = myStock.getAum();
                myStock.setAum(prevAum + totalPrice);

                stockService.saveStock(myStock);

                // save in transaction table

                Transaction transaction = new Transaction();
                transaction.setCostPrice(totalPrice);


                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formatDateTime = now.format(format);
                transaction.setLocalDateTime(now);

                transaction.setUserId(myUser.getId());
                transaction.setType("Buy");
                transaction.setQuantity(transactionDto.getQuantity());
                transaction.setStockName(myStock.getSymbol());
                transactionService.saveTransaction(transaction);


                return "redirect:/transaction?success";

            }

        }
        else if(transactionDto.getOrderType().equals("Sell")) {
            Ownership ownership = ownershipService.getOwnership(transactionDto.getStockId(), myUser.getId());
            Stock myStock = stockService.getIndividualStock(transactionDto.getStockId());
            if(ownership == null || ownership.getQuantity() < transactionDto.getQuantity()) {

                    return "redirect:/transaction?failure";

            }
            else {

                // modification in user table

                //change wallet
                Long prev = myUser.getWallet();
                prev += totalPrice;

                //update wallet
                myUser.setWallet(prev);

                //update invested value

                Long costPerStock = ownership.getCostPrice()/ownership.getQuantity();

                Long prevInvestedValue = myUser.getInvestedValue();
                prevInvestedValue -= transactionDto.getQuantity() * costPerStock;
                myUser.setInvestedValue(prevInvestedValue);

                //save the user data
                userService.updateNewUser(myUser);

                // modification in ownership table
                int newOwner = 0;
//                Ownership ownership = ownershipService.getOwnership(transactionDto.getStockId(), myUser.getId());

                // destroy ownership
                if(ownership.getQuantity() == transactionDto.getQuantity()) {
                    newOwner -= 1;

                    ownershipService.deleteOwnership(myUser.getId(), myStock.getId());
                }
                else {
                    // update ownership
                    Long prevCostPrice = ownership.getCostPrice();
                    Long prevQuantity = ownership.getQuantity();
                    ownership.setCostPrice(prevCostPrice - (transactionDto.getQuantity() * costPerStock));
                    ownership.setQuantity(prevQuantity - transactionDto.getQuantity());
                    ownershipService.saveOwnership(ownership);
                }


                // change in stock table



                if(newOwner == -1) {
                    long prevOwners = myStock.getInvestedUsers();
                    myStock.setInvestedUsers(prevOwners + newOwner);
                }

                Long prevAum = myStock.getAum();
                myStock.setAum(prevAum - totalPrice);

                stockService.saveStock(myStock);

                // save in transaction table

                Transaction transaction = new Transaction();
                transaction.setCostPrice(totalPrice);


                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formatDateTime = now.format(format);
                transaction.setLocalDateTime(now);

                transaction.setUserId(myUser.getId());
                transaction.setType("Sell");
                transaction.setQuantity(transactionDto.getQuantity());
                transaction.setStockName(myStock.getSymbol());
                transactionService.saveTransaction(transaction);


                return "redirect:/transaction?success";

            }
        }
        else {
            return "redirect:/transaction?failure";
        }

    }

}
