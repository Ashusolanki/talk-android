/*
 *
 *   Nextcloud Talk application
 *
 *   @author Mario Danic
 *   Copyright (C) 2017 Mario Danic
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.nextcloud.talk.api;

import com.nextcloud.talk.api.models.json.call.CallOverall;
import com.nextcloud.talk.api.models.json.generic.Status;
import com.nextcloud.talk.api.models.json.participants.AddParticipantOverall;
import com.nextcloud.talk.api.models.json.participants.ParticipantsOverall;
import com.nextcloud.talk.api.models.json.rooms.RoomOverall;
import com.nextcloud.talk.api.models.json.rooms.RoomsOverall;
import com.nextcloud.talk.api.models.json.sharees.ShareesOverall;
import com.nextcloud.talk.api.models.json.userprofile.UserProfileOverall;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface NcApi {

    /*
        QueryMap items are as follows:
            - "format" : "json"
            - "search" : ""
            - "perPage" : "200"
            - "itemType" : "call"

        Server URL is: baseUrl + ocsApiVersion + /apps/files_sharing/api/v1/sharees
     */
    @GET
    Observable<ShareesOverall> getContactsWithSearchParam(@Header("Authorization") String authorization, @Url String url,
                                                          @QueryMap Map<String, String> options);


    /*
        Server URL is: baseUrl + ocsApiVersion + spreedApiVersion + /room
     */
    @GET
    Observable<RoomsOverall> getRooms(@Header("Authorization") String authorization, @Url String url);

    /*
        Server URL is: baseUrl + ocsApiVersion + spreedApiVersion + /room/roomToken
    */
    @GET
    Observable<RoomOverall> getRoom(@Header("Authorization") String authorization, @Url String url);

    /*
        QueryMap items are as follows:
            - "roomType" : ""
            - "invite" : ""

        Server URL is: baseUrl + ocsApiVersion + spreedApiVersion + /room
     */

    @POST
    Observable<RoomOverall> createRoom(@Header("Authorization") String authorization, @Url String url,
                                       @QueryMap Map<String, String> options);

    /*
        QueryMap items are as follows:
            - "roomName" : "newName"

        Server URL is: baseUrl + ocsApiVersion + spreedApiVersion + /room/roomToken
     */

    @PUT
    Observable<Void> renameRoom(@Header("Authorization") String authorization, @Url String url,
                                @QueryMap Map<String, String> options);


    /*
        QueryMap items are as follows:
            - "newParticipant" : "user"

        Server URL is: baseUrl + ocsApiVersion + spreedApiVersion + /room/roomToken/participants
    */
    @POST
    Observable<AddParticipantOverall> addParticipant(@Header("Authorization") String authorization, @Url String url,
                                                     @QueryMap Map<String, String> options);


    /*
        Server URL is: baseUrl + ocsApiVersion + spreedApiVersion + /room/roomToken/participants/self
     */

    @DELETE
    Observable<Void> removeSelfFromRoom(@Header("Authorization") String authorization, @Url String url);

    /*
        Server URL is: baseUrl + ocsApiVersion + spreedApiVersion + /room/roomToken/public
    */
    @POST
    Observable<Void> makeRoomPublic(@Header("Authorization") String authorization, @Url String url);

    /*
        Server URL is: baseUrl + ocsApiVersion + spreedApiVersion + /room/roomToken/public
    */
    @DELETE
    Observable<Void> makeRoomPrivate(@Header("Authorization") String authorization, @Url String url);

    /*
        Server URL is: baseUrl + ocsApiVersion + spreedApiVersion + /call/callToken
    */
    @GET
    Observable<ParticipantsOverall> getPeersForCall(@Header("Authorization") String authorization, @Url String url);

    /*
        Server URL is: baseUrl + ocsApiVersion + spreedApiVersion + /call/callToken
    */
    @POST
    Observable<CallOverall> joinCall(@Header("Authorization") String authorization, @Url String url);

    /*
        Server URL is: baseUrl + ocsApiVersion + spreedApiVersion + /call/callToken/ping
    */
    @POST
    Observable<Void> pingCall(@Header("Authorization") String authorization, @Url String url);

    /*
        Server URL is: baseUrl + ocsApiVersion + spreedApiVersion + /call/callToken
    */
    @DELETE
    Observable<Void> leaveCall(@Header("Authorization") String authorization, @Url String url);

    /*
        QueryMap items are as follows:
            - "messages" : "message"

        Server URL is: baseUrl + ocsApiVersion + spreedApiVersion + /signaling
    */
    @POST
    Observable<Integer> sendSignalingMessages(@Header("Authorization") String authorization, @Url String url);

    /*
        Server URL is: baseUrl + ocsApiVersion + spreedApiVersion + /signaling
    */
    @GET
    Observable<Integer> pullSignalingMessages(@Header("Authorization") String authorization, @Url String url);

    /*
        Server URL is: baseUrl + /index.php/avatar/userId/128
        Avatar OkHttp
    */

     /*
        QueryMap items are as follows:
            - "format" : "json"

        Server URL is: baseUrl + ocsApiVersion + "/cloud/user"
    */

    @GET
    Observable<UserProfileOverall> getUserProfile(@Header("Authorization") String authorization, @Url String url);

    /*
        Server URL is: baseUrl + /status.php
     */
    @GET
    Observable<Status> getServerStatus(@Url String url);
}