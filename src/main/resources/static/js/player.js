//Power buttons
$('.power').click(function() {
	if ($(this).hasClass("clicked")) {
		playerPower(getPlayerNameById($(this).attr("id")), "OFF");
	} else {
		playerPower(getPlayerNameById($(this).attr("id")), "ON");
	}
	$(this).toggleClass('clicked');
	updatePlayerControls();
});

$('.pause').hide(); // hide pause button until clicked

// play button
$('.play').click(function() {
	var playerName = getPlayerNameById($(this).attr("id"));
	// Find the button that starts with pause
	toggleButton(this, "[id^=pause]");

	playButton(getPlayerNameById($(this).attr("id")));
});

//next button
$('.next').click(function() {
	var playerName = getPlayerNameById($(this).attr("id"));
	// Find the button that starts with pause
	// updatePlayerControls();

	skipNext(getPlayerNameById($(this).attr("id")));
});

//next button
$('.previous').click(function() {
	var playerName = getPlayerNameById($(this).attr("id"));
	// Find the button that starts with pause
	// updatePlayerControls();

	skipPrev(getPlayerNameById($(this).attr("id")));
});

// pause button
$('.pause').click(function() {
	var playerName = getPlayerNameById($(this).attr("id"));
	// Find the button that starts with play
	toggleButton(this, "[id^=play]");

	playerPause(getPlayerNameById($(this).attr("id")));
});

function toggleButton(currentButton, controlPrefix) {
	var parent = $(currentButton).parent();
	var buttonTwin = parent.find(controlPrefix);

	if ($(currentButton).hasClass("clicked")) {
		$(currentButton).toggleClass('clicked');
		$(buttonTwin).hide();
		$(currentButton).show();
	} else {
		$(currentButton).toggleClass('clicked');
		$(buttonTwin).show();
		$(currentButton).hide();
	}
	if ($(buttonTwin).hasClass("clicked")) {
		$(buttonTwin).toggleClass('clicked');
	}
}

// Volume controls
$('.slider').on('input', function() {
	playerVolume(getPlayerNameById($(this).attr("id")), this.value);
});

function updateNowPlaying() {
	setInterval(function() {
		updateTrackInfo();
	}, 5000);
}

// Update the power control states
// If a player's power is on, enable the other controls also
function updateControlStates() {
	$('.power').each(function(index, element) {
		var playerName = getPlayerNameById($(element).attr("id"));
		var powerState = playerPower(playerName, "?");
		var volumeControl = getControlNameFromId(playerName, "volume");
		var playControl = getControlNameFromId(playerName, "play");
		var pauseControl = getControlNameFromId(playerName, "pause");
		var nextControl = getControlNameFromId(playerName, "next");
		var prevControl = getControlNameFromId(playerName, "prev");
		if (powerState) {
			if (!$(element).hasClass('clicked')) {
				$(element).addClass('clicked');
			}
			$(volumeControl).prop('disabled', false);
			if (!$(pauseControl).hasClass('clicked')) {
				$(playControl).hide();
				$(pauseControl).show();
			} else {
				$(playControl).show();
				$(pauseControl).hide();
			}
			$(nextControl).show();
			$(prevControl).show();
		} else {
			if ($(element).hasClass('clicked')) {
				$(element).removeClass('clicked');
			}
			$(volumeControl).prop('disabled', true);
			$(playControl).hide();
			$(pauseControl).hide();
			$(nextControl).hide();
			$(prevControl).hide();
		}
		$(volumeControl).change();
	});
}

// Update the volume controls
function updateVolumeControls() {
	$('.slider').each(
			function(index, element) {
				var volumeLevel = playerVolume(getPlayerNameById($(element)
						.attr("id")), "?");
				$(element).attr('value', parseInt(volumeLevel));
				$(element).trigger('input');
			});
}

/**
 * Enables or disables the power and volume controls based on the actual
 * hardware status.
 */
function updatePlayerControls() {
	updateControlStates();
	updateVolumeControls();
}
