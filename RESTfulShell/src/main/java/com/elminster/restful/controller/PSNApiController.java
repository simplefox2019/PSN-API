package com.elminster.restful.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elminster.restful.service.IUserGameService;
import com.elminster.restful.service.IUserGameTrophyService;
import com.elminster.retrieve.data.user.PSNUserGame;
import com.elminster.retrieve.data.user.PSNUserProfile;
import com.elminster.retrieve.data.user.PSNUserTrophy;
import com.elminster.retrieve.exception.ServiceException;
import com.elminster.retrieve.service.IPSNApi;
import com.elminster.retrieve.service.PSNApiImpl;

/**
 * The PSN api RESTful controller.
 * 
 * @author jgu
 * @version 1.0
 */
@Controller
public class PSNApiController {
  /** the PSN API. */
  private static final IPSNApi API = new PSNApiImpl();
  
  private final IUserGameTrophyService userGameTrophyService;
  private final IUserGameService userGameService;
  
  @Autowired
  public PSNApiController(IUserGameService userGameService, IUserGameTrophyService userGameTrophyService) {
    this.userGameService = userGameService;
    this.userGameTrophyService = userGameTrophyService;
  }

  @RequestMapping(value = "/userProfile/{username}", method = RequestMethod.GET)
  public @ResponseBody PSNUserProfile getUserProfile(@PathVariable("username") String username)
      throws ServiceException {
    return API.getPSNUserProfile(username);
  }

  @RequestMapping(value = "/userGameList/{username}", method = RequestMethod.GET)
  public @ResponseBody List<PSNUserGame> getUserGameList(
      @PathVariable("username") String username) throws ServiceException {
    return userGameService.getUserGameList(username);
  }
  
  @RequestMapping(value = "/userGameTrophyList/{username}/{gameId}", method = RequestMethod.GET)
  public @ResponseBody List<PSNUserTrophy> getUserGameTrophyList(
      @PathVariable("username") String username, @PathVariable("gameId") String gameId) throws ServiceException {
    return userGameTrophyService.getUserGameTrophyList(username, gameId);
  }
}
