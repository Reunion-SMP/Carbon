SELECT
    id,
    muted,
    muteexpiration,
    deafened,
    selectedchannel,
    displayname,
    lastwhispertarget,
    whisperreplytarget,
    spying,
    ignoringdms,
    party,
    applycustomfilters,
    nochatformat
FROM carbon_users WHERE (id = :id);
