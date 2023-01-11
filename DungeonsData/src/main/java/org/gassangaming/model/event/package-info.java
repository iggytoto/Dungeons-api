/**
 * <h> {@link org.gassangaming.model.event.Event} </h>
 * <p>
 * Package contains database model for the events and its processing by the servers.
 * {@link org.gassangaming.model.event.Event} - is a event that occurs at point of time. When it occurs it is in state
 * {@link org.gassangaming.model.event.EventStatus#Planned} , it means that registration on this event is open and players
 * can register to it. Sometime after event goes into {@link org.gassangaming.model.event.EventStatus#InProgress} that
 * means that processing of event already started and on this event nobody can longer register. After event is processed
 * it goes to the {@link org.gassangaming.model.event.EventStatus#Finished} state for application of post processing
 * activities and then goes to {@link org.gassangaming.model.event.EventStatus#Closed}.
 * </p>
 * <h>{@link org.gassangaming.model.event.EventInstance}</h>
 * <p>
 * Event instance is an entry that describes concrete instance of the event, as many players can register to one event
 * they need to be split by batches to process them separately by servers. Each server at one point of time processes
 * one event instance. {@link org.gassangaming.model.event.EventInstanceStatus} represents state of instance.
 * {@link org.gassangaming.model.event.EventInstanceStatus#WaitingForServer} means that event already in the progress
 * and this concrete instance can be picked up by server and processed. After server picks concrete instance for
 * processing instance goes to {@link org.gassangaming.model.event.EventInstanceStatus#WaitingForPlayers}. Means
 * that server already loaded and prepared for instance processing and awaits for some time for players to connect to
 * watch the event. After sometime server goes into {@link org.gassangaming.model.event.EventInstanceStatus#InProgress}
 * processes the instance saving the results and deletes the instance.
 * </p>
 * <h>{@link org.gassangaming.model.event.UnitEventRegistration}</h>
 * <p>
 * Unit event registration is an utility table to indicate which unit of which player is registered to which event.
 * </p>
 * <h>{@link org.gassangaming.model.event.UserEventInstance}</h>
 * <p>
 * When instance is created UserEventInstance holds which user assigned to which instance of event.
 * </p>
 * <h>{@link org.gassangaming.model.event.UserEventRegistration}</h>
 * <p>
 * Holds data about which user is registered to which event.
 * </p>
 */
package org.gassangaming.model.event;