
/*
#################################################################################
#	Javascript Cookies - based on the tutorial @ http://www.quirksmode.org
#################################################################################
*/

/* base cookie management functions */

function createCookie(name, value, days) {
	if (days) {
		var date = new Date();
		date.setTime(date.getTime()+(days*24*60*60*1000));
		var expires = "; expires="+date.toGMTString();
	}
	else var expires = "";
	document.cookie = name+"="+value+expires+"; path=/";
}

function eraseCookie(name) {
	createCookie(name, "", -1);
}

function readCookie(name) {
	var ca = document.cookie.split(';');
	var nameEQ = name + "=";
	for(var i=0; i < ca.length; i++) {
		var c = ca[i];
		c = c.replace(/^\s*|\s*$/g,"");
		if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
	}
	return "";
}

/* end */


/*
#################################################################################
#
# Copyright (c) 2006 Michigan State University
#
# This program is free software; you can redistribute it and/or
# modify it under the terms of the GNU General Public License
# as published by the Free Software Foundation; either version 2
# of the License, or (at your option) any later version.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program; if not, write to the Free Software
# Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
#################################################################################

#################################################################################
#
#	Author:
#		Nathan Collins <npcollins@gmail.com>
#
#################################################################################
*/

/* append a cookie */

function appendCookie(name,append) {
	var val = readCookie(name);
	createCookie(name,val+append);
}

/* end */


/* add to a cookie list */

function addCookieList(name, val) {
	/* the largest this list can get */
	var maxList = 8;

	/* pull existing cookie list values */
	var oldList = getCookieList(name);

	/* remove any duplicates already in the list */
	for (elem in oldList) {
		if (val == oldList[elem].replace("\\!",":")) {
			oldList.splice(elem,1);
			break;
		}
	}

	/* if this add causes the list length to be greater then the max */
	if (oldList.length == maxList) {
		/* then remove the oldest(first) element in the list */
		trash = oldList.shift();
	}

	/* escape any colons */
	val = val.replace(":","\\!");

	/* add to list */
	oldList.push(val);

	/* convert to : delimited string */
	newList = oldList.join(":");

	/* set cookie */
	createCookie(name, newList);
}

/* end */


/* pull an array from a cookie list with : delimiter */

function getCookieList(name) {
	c = readCookie(name);
	cArr = c.split(":");
	retArr = new Array();

	/* loop through the split cookie */
	for (i=0; i<cArr.length; i++) {

		if (cArr[i].length > 0) {
			/* unescape colons */
			listElem = cArr[i].replace("\\!",":");

			/* add to return array */
			retArr.push(listElem);
		}
	}
	return retArr;
}

/* end */


/* remember and recall location functions */

function rememberLocation() {
	createCookie('suggestReturn',window.location);
}

function recallLocation() {
	loc = readCookie('suggestReturn');
	window.location = loc
	return false;
}

/* end */
