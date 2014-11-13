<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.lang.String"  %>
<%@taglib prefix="s" uri="/struts-tags" %>

		
	<form name="form" action="/orderConfirmation/orderConfirmationBatch.do" id="cart_frm_settle" method="post">
		<input type="hidden" name="products" id="products"/>
		<input type="hidden" name="buycounts" id="buycounts"/>
		<input type="hidden" name="shipfees" id="shipfees"/>
		<input type="hidden" name="profees" id="profees"/>
		<input type="hidden" name="singlefees" id="singlefees"/>
		<input type="hidden" name="sellers" id="sellers"/>
	
	<s:set name="productlist" value="#request.list"/>
	<s:if test="#productlist.size>0">
	<div  class="tblWhite">
		<div>Ship my order(s) to :</div>
		<p></p>
		<div>
	   	  <select name="countries" id="countries" style="width:300px;" onchange="setfreight()">
	      <option value='ad' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag ad" data-title="Andorra">Andorra</option>
	      <option value='ae' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag ae" data-title="United Arab Emirates">United Arab Emirates</option>
	      <option value='af' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag af" data-title="Afghanistan">Afghanistan</option>
	      <option value='ag' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag ag" data-title="Antigua and Barbuda">Antigua and Barbuda</option>
	      <option value='ai' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag ai" data-title="Anguilla">Anguilla</option>
	      <option value='al' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag al" data-title="Albania">Albania</option>
	      <option value='am' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag am" data-title="Armenia">Armenia</option>
	      <option value='an' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag an" data-title="Netherlands Antilles">Netherlands Antilles</option>
	      <option value='ao' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag ao" data-title="Angola">Angola</option>
	      <option value='aq' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag aq" data-title="Antarctica">Antarctica</option>
	      <option value='ar' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag ar" data-title="Argentina">Argentina</option>
	      <option value='as' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag as" data-title="American Samoa">American Samoa</option>
	      <option value='at' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag at" data-title="Austria">Austria</option>
	      <option value='au' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag au" data-title="Australia">Australia</option>
	      <option value='aw' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag aw" data-title="Aruba">Aruba</option>
	      <option value='ax' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag ax" data-title="Aland Islands">Aland Islands</option>
	      <option value='az' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag az" data-title="Azerbaijan">Azerbaijan</option>
	      <option value='ba' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag ba" data-title="Bosnia and Herzegovina">Bosnia and Herzegovina</option>
	      <option value='bb' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag bb" data-title="Barbados">Barbados</option>
	      <option value='bd' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag bd" data-title="Bangladesh">Bangladesh</option>
	      <option value='be' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag be" data-title="Belgium">Belgium</option>
	      <option value='bf' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag bf" data-title="Burkina Faso">Burkina Faso</option>
	      <option value='bg' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag bg" data-title="Bulgaria">Bulgaria</option>
	      <option value='bh' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag bh" data-title="Bahrain">Bahrain</option>
	      <option value='bi' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag bi" data-title="Burundi">Burundi</option>
	      <option value='bj' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag bj" data-title="Benin">Benin</option>
	      <option value='bm' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag bm" data-title="Bermuda">Bermuda</option>
	      <option value='bn' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag bn" data-title="Brunei Darussalam">Brunei Darussalam</option>
	      <option value='bo' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag bo" data-title="Bolivia">Bolivia</option>
	      <option value='br' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag br" data-title="Brazil">Brazil</option>
	      <option value='bs' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag bs" data-title="Bahamas">Bahamas</option>
	      <option value='bt' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag bt" data-title="Bhutan">Bhutan</option>
	      <option value='bv' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag bv" data-title="Bouvet Island">Bouvet Island</option>
	      <option value='bw' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag bw" data-title="Botswana">Botswana</option>
	      <option value='by' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag by" data-title="Belarus">Belarus</option>
	      <option value='bz' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag bz" data-title="Belize">Belize</option>
	      <option value='ca' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag ca" data-title="Canada">Canada</option>
	      <option value='cc' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag cc" data-title="Cocos (Keeling) Islands">Cocos (Keeling) Islands</option>
	      <option value='cd' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag cd" data-title="Democratic Republic of the Congo">Democratic Republic of the Congo</option>
	      <option value='cf' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag cf" data-title="Central African Republic">Central African Republic</option>
	      <option value='cg' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag cg" data-title="Congo">Congo</option>
	      <option value='ch' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag ch" data-title="Switzerland">Switzerland</option>
	      <option value='ci' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag ci" data-title="Cote D'Ivoire (Ivory Coast)">Cote D'Ivoire (Ivory Coast)</option>
	      <option value='ck' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag ck" data-title="Cook Islands">Cook Islands</option>
	      <option value='cl' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag cl" data-title="Chile">Chile</option>
	      <option value='cm' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag cm" data-title="Cameroon">Cameroon</option>
	      <option value='cn' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag cn" data-title="China">China</option>
	      <option value='co' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag co" data-title="Colombia">Colombia</option>
	      <option value='cr' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag cr" data-title="Costa Rica">Costa Rica</option>
	      <option value='cs' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag cs" data-title="Serbia and Montenegro">Serbia and Montenegro</option>
	      <option value='cu' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag cu" data-title="Cuba">Cuba</option>
	      <option value='cv' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag cv" data-title="Cape Verde">Cape Verde</option>
	      <option value='cx' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag cx" data-title="Christmas Island">Christmas Island</option>
	      <option value='cy' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag cy" data-title="Cyprus">Cyprus</option>
	      <option value='cz' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag cz" data-title="Czech Republic">Czech Republic</option>
	      <option value='de' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag de" data-title="Germany">Germany</option>
	      <option value='dj' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag dj" data-title="Djibouti">Djibouti</option>
	      <option value='dk' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag dk" data-title="Denmark">Denmark</option>
	      <option value='dm' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag dm" data-title="Dominica">Dominica</option>
	      <option value='do' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag do" data-title="Dominican Republic">Dominican Republic</option>
	      <option value='dz' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag dz" data-title="Algeria">Algeria</option>
	      <option value='ec' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag ec" data-title="Ecuador">Ecuador</option>
	      <option value='ee' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag ee" data-title="Estonia">Estonia</option>
	      <option value='eg' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag eg" data-title="Egypt">Egypt</option>
	      <option value='eh' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag eh" data-title="Western Sahara">Western Sahara</option>
	      <option value='er' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag er" data-title="Eritrea">Eritrea</option>
	      <option value='es' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag es" data-title="Spain">Spain</option>
	      <option value='et' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag et" data-title="Ethiopia">Ethiopia</option>
	      <option value='fi' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag fi" data-title="Finland">Finland</option>
	      <option value='fj' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag fj" data-title="Fiji">Fiji</option>
	      <option value='fk' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag fk" data-title="Falkland Islands (Malvinas)">Falkland Islands (Malvinas)</option>
	      <option value='fm' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag fm" data-title="Federated States of Micronesia">Federated States of Micronesia</option>
	      <option value='fo' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag fo" data-title="Faroe Islands">Faroe Islands</option>
	      <option value='fr' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag fr" data-title="France">France</option>
	      <option value='fx' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag fx" data-title="France, Metropolitan">France, Metropolitan</option>
	      <option value='ga' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag ga" data-title="Gabon">Gabon</option>
	      <option value='gb' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag gb" data-title="Great Britain (UK)">Great Britain (UK)</option>
	      <option value='gd' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag gd" data-title="Grenada">Grenada</option>
	      <option value='ge' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag ge" data-title="Georgia">Georgia</option>
	      <option value='gf' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag gf" data-title="French Guiana">French Guiana</option>
	      <option value='gh' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag gh" data-title="Ghana">Ghana</option>
	      <option value='gi' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag gi" data-title="Gibraltar">Gibraltar</option>
	      <option value='gl' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag gl" data-title="Greenland">Greenland</option>
	      <option value='gm' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag gm" data-title="Gambia">Gambia</option>
	      <option value='gn' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag gn" data-title="Guinea">Guinea</option>
	      <option value='gp' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag gp" data-title="Guadeloupe">Guadeloupe</option>
	      <option value='gq' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag gq" data-title="Equatorial Guinea">Equatorial Guinea</option>
	      <option value='gr' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag gr" data-title="Greece">Greece</option>
	      <option value='gs' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag gs" data-title="S. Georgia and S. Sandwich Islands">S. Georgia and S. Sandwich Islands</option>
	      <option value='gt' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag gt" data-title="Guatemala">Guatemala</option>
	      <option value='gu' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag gu" data-title="Guam">Guam</option>
	      <option value='gw' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag gw" data-title="Guinea-Bissau">Guinea-Bissau</option>
	      <option value='gy' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag gy" data-title="Guyana">Guyana</option>
	      <option value='hk' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag hk" data-title="Hong Kong">Hong Kong</option>
	      <option value='hm' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag hm" data-title="Heard Island and McDonald Islands">Heard Island and McDonald Islands</option>
	      <option value='hn' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag hn" data-title="Honduras">Honduras</option>
	      <option value='hr' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag hr" data-title="Croatia (Hrvatska)">Croatia (Hrvatska)</option>
	      <option value='ht' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag ht" data-title="Haiti">Haiti</option>
	      <option value='hu' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag hu" data-title="Hungary">Hungary</option>
	      <option value='id' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag id" data-title="Indonesia">Indonesia</option>
	      <option value='ie' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag ie" data-title="Ireland">Ireland</option>
	      <option value='il' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag il" data-title="Israel">Israel</option>
	      <option value='in' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag in" data-title="India">India</option>
	      <option value='io' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag io" data-title="British Indian Ocean Territory">British Indian Ocean Territory</option>
	      <option value='iq' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag iq" data-title="Iraq">Iraq</option>
	      <option value='ir' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag ir" data-title="Iran">Iran</option>
	      <option value='is' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag is" data-title="Iceland">Iceland</option>
	      <option value='it' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag it" data-title="Italy">Italy</option>
	      <option value='jm' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag jm" data-title="Jamaica">Jamaica</option>
	      <option value='jo' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag jo" data-title="Jordan">Jordan</option>
	      <option value='jp' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag jp" data-title="Japan">Japan</option>
	      <option value='ke' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag ke" data-title="Kenya">Kenya</option>
	      <option value='kg' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag kg" data-title="Kyrgyzstan">Kyrgyzstan</option>
	      <option value='kh' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag kh" data-title="Cambodia">Cambodia</option>
	      <option value='ki' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag ki" data-title="Kiribati">Kiribati</option>
	      <option value='km' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag km" data-title="Comoros">Comoros</option>
	      <option value='kn' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag kn" data-title="Saint Kitts and Nevis">Saint Kitts and Nevis</option>
	      <option value='kp' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag kp" data-title="Korea (North)">Korea (North)</option>
	      <option value='kr' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag kr" data-title="Korea (South)">Korea (South)</option>
	      <option value='kw' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag kw" data-title="Kuwait">Kuwait</option>
	      <option value='ky' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag ky" data-title="Cayman Islands">Cayman Islands</option>
	      <option value='kz' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag kz" data-title="Kazakhstan">Kazakhstan</option>
	      <option value='la' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag la" data-title="Laos">Laos</option>
	      <option value='lb' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag lb" data-title="Lebanon">Lebanon</option>
	      <option value='lc' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag lc" data-title="Saint Lucia">Saint Lucia</option>
	      <option value='li' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag li" data-title="Liechtenstein">Liechtenstein</option>
	      <option value='lk' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag lk" data-title="Sri Lanka">Sri Lanka</option>
	      <option value='lr' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag lr" data-title="Liberia">Liberia</option>
	      <option value='ls' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag ls" data-title="Lesotho">Lesotho</option>
	      <option value='lt' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag lt" data-title="Lithuania">Lithuania</option>
	      <option value='lu' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag lu" data-title="Luxembourg">Luxembourg</option>
	      <option value='lv' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag lv" data-title="Latvia">Latvia</option>
	      <option value='ly' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag ly" data-title="Libya">Libya</option>
	      <option value='ma' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag ma" data-title="Morocco">Morocco</option>
	      <option value='mc' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag mc" data-title="Monaco">Monaco</option>
	      <option value='md' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag md" data-title="Moldova">Moldova</option>
	      <option value='mg' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag mg" data-title="Madagascar">Madagascar</option>
	      <option value='mh' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag mh" data-title="Marshall Islands">Marshall Islands</option>
	      <option value='mk' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag mk" data-title="Macedonia">Macedonia</option>
	      <option value='ml' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag ml" data-title="Mali">Mali</option>
	      <option value='mm' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag mm" data-title="Myanmar">Myanmar</option>
	      <option value='mn' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag mn" data-title="Mongolia">Mongolia</option>
	      <option value='mo' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag mo" data-title="Macao">Macao</option>
	      <option value='mp' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag mp" data-title="Northern Mariana Islands">Northern Mariana Islands</option>
	      <option value='mq' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag mq" data-title="Martinique">Martinique</option>
	      <option value='mr' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag mr" data-title="Mauritania">Mauritania</option>
	      <option value='ms' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag ms" data-title="Montserrat">Montserrat</option>
	      <option value='mt' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag mt" data-title="Malta">Malta</option>
	      <option value='mu' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag mu" data-title="Mauritius">Mauritius</option>
	      <option value='mv' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag mv" data-title="Maldives">Maldives</option>
	      <option value='mw' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag mw" data-title="Malawi">Malawi</option>
	      <option value='mx' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag mx" data-title="Mexico">Mexico</option>
	      <option value='my' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag my" data-title="Malaysia">Malaysia</option>
	      <option value='mz' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag mz" data-title="Mozambique">Mozambique</option>
	      <option value='na' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag na" data-title="Namibia">Namibia</option>
	      <option value='nc' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag nc" data-title="New Caledonia">New Caledonia</option>
	      <option value='ne' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag ne" data-title="Niger">Niger</option>
	      <option value='nf' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag nf" data-title="Norfolk Island">Norfolk Island</option>
	      <option value='ng' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag ng" data-title="Nigeria">Nigeria</option>
	      <option value='ni' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag ni" data-title="Nicaragua">Nicaragua</option>
	      <option value='nl' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag nl" data-title="Netherlands">Netherlands</option>
	      <option value='no' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag no" data-title="Norway">Norway</option>
	      <option value='np' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag np" data-title="Nepal">Nepal</option>
	      <option value='nr' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag nr" data-title="Nauru">Nauru</option>
	      <option value='nu' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag nu" data-title="Niue">Niue</option>
	      <option value='nz' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag nz" data-title="New Zealand (Aotearoa)">New Zealand (Aotearoa)</option>
	      <option value='om' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag om" data-title="Oman">Oman</option>
	      <option value='pa' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag pa" data-title="Panama">Panama</option>
	      <option value='pe' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag pe" data-title="Peru">Peru</option>
	      <option value='pf' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag pf" data-title="French Polynesia">French Polynesia</option>
	      <option value='pg' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag pg" data-title="Papua New Guinea">Papua New Guinea</option>
	      <option value='ph' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag ph" data-title="Philippines">Philippines</option>
	      <option value='pk' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag pk" data-title="Pakistan">Pakistan</option>
	      <option value='pl' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag pl" data-title="Poland">Poland</option>
	      <option value='pm' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag pm" data-title="Saint Pierre and Miquelon">Saint Pierre and Miquelon</option>
	      <option value='pn' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag pn" data-title="Pitcairn">Pitcairn</option>
	      <option value='pr' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag pr" data-title="Puerto Rico">Puerto Rico</option>
	      <option value='ps' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag ps" data-title="Palestinian Territory">Palestinian Territory</option>
	      <option value='pt' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag pt" data-title="Portugal">Portugal</option>
	      <option value='pw' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag pw" data-title="Palau">Palau</option>
	      <option value='py' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag py" data-title="Paraguay">Paraguay</option>
	      <option value='qa' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag qa" data-title="Qatar">Qatar</option>
	      <option value='re' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag re" data-title="Reunion">Reunion</option>
	      <option value='ro' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag ro" data-title="Romania">Romania</option>
	      <option value='ru' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag ru" data-title="Russian Federation">Russian Federation</option>
	      <option value='rw' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag rw" data-title="Rwanda">Rwanda</option>
	      <option value='sa' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag sa" data-title="Saudi Arabia">Saudi Arabia</option>
	      <option value='sb' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag sb" data-title="Solomon Islands">Solomon Islands</option>
	      <option value='sc' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag sc" data-title="Seychelles">Seychelles</option>
	      <option value='sd' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag sd" data-title="Sudan">Sudan</option>
	      <option value='se' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag se" data-title="Sweden">Sweden</option>
	      <option value='sg' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag sg" data-title="Singapore">Singapore</option>
	      <option value='sh' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag sh" data-title="Saint Helena">Saint Helena</option>
	      <option value='si' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag si" data-title="Slovenia">Slovenia</option>
	      <option value='sj' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag sj" data-title="Svalbard and Jan Mayen">Svalbard and Jan Mayen</option>
	      <option value='sk' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag sk" data-title="Slovakia">Slovakia</option>
	      <option value='sl' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag sl" data-title="Sierra Leone">Sierra Leone</option>
	      <option value='sm' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag sm" data-title="San Marino">San Marino</option>
	      <option value='sn' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag sn" data-title="Senegal">Senegal</option>
	      <option value='so' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag so" data-title="Somalia">Somalia</option>
	      <option value='sr' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag sr" data-title="Suriname">Suriname</option>
	      <option value='st' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag st" data-title="Sao Tome and Principe">Sao Tome and Principe</option>
	      <option value='su' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag su" data-title="USSR (former)">USSR (former)</option>
	      <option value='sv' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag sv" data-title="El Salvador">El Salvador</option>
	      <option value='sy' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag sy" data-title="Syria">Syria</option>
	      <option value='sz' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag sz" data-title="Swaziland">Swaziland</option>
	      <option value='tc' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag tc" data-title="Turks and Caicos Islands">Turks and Caicos Islands</option>
	      <option value='td' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag td" data-title="Chad">Chad</option>
	      <option value='tf' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag tf" data-title="French Southern Territories">French Southern Territories</option>
	      <option value='tg' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag tg" data-title="Togo">Togo</option>
	      <option value='th' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag th" data-title="Thailand">Thailand</option>
	      <option value='tj' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag tj" data-title="Tajikistan">Tajikistan</option>
	      <option value='tk' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag tk" data-title="Tokelau">Tokelau</option>
	      <option value='tl' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag tl" data-title="Timor-Leste">Timor-Leste</option>
	      <option value='tm' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag tm" data-title="Turkmenistan">Turkmenistan</option>
	      <option value='tn' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag tn" data-title="Tunisia">Tunisia</option>
	      <option value='to' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag to" data-title="Tonga">Tonga</option>
	      <option value='tp' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag tp" data-title="East Timor">East Timor</option>
	      <option value='tr' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag tr" data-title="Turkey">Turkey</option>
	      <option value='tt' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag tt" data-title="Trinidad and Tobago">Trinidad and Tobago</option>
	      <option value='tv' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag tv" data-title="Tuvalu">Tuvalu</option>
	      <option value='tw' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag tw" data-title="Taiwan">Taiwan</option>
	      <option value='tz' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag tz" data-title="Tanzania">Tanzania</option>
	      <option value='ua' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag ua" data-title="Ukraine">Ukraine</option>
	      <option value='ug' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag ug" data-title="Uganda">Uganda</option>
	      <option value='uk' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag uk" data-title="United Kingdom">United Kingdom</option>
	      <option value='um' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag um" data-title="United States Minor Outlying Islands">United States Minor Outlying Islands</option>
	      <option value='us' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag us" data-title="United States">United States</option>
	      <option value='uy' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag uy" data-title="Uruguay">Uruguay</option>
	      <option value='uz' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag uz" data-title="Uzbekistan">Uzbekistan</option>
	      <option value='va' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag va" data-title="Vatican City State (Holy See)">Vatican City State (Holy See)</option>
	      <option value='vc' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag vc" data-title="Saint Vincent and the Grenadines">Saint Vincent and the Grenadines</option>
	      <option value='ve' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag ve" data-title="Venezuela">Venezuela</option>
	      <option value='vg' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag vg" data-title="Virgin Islands (British)">Virgin Islands (British)</option>
	      <option value='vi' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag vi" data-title="Virgin Islands (U.S.)">Virgin Islands (U.S.)</option>
	      <option value='vn' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag vn" data-title="Viet Nam">Viet Nam</option>
	      <option value='vu' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag vu" data-title="Vanuatu">Vanuatu</option>
	      <option value='wf' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag wf" data-title="Wallis and Futuna">Wallis and Futuna</option>
	      <option value='ws' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag ws" data-title="Samoa">Samoa</option>
	      <option value='ye' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag ye" data-title="Yemen">Yemen</option>
	      <option value='yt' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag yt" data-title="Mayotte">Mayotte</option>
	      <option value='yu' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag yu" data-title="Yugoslavia (former)">Yugoslavia (former)</option>
	      <option value='za' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag za" data-title="South Africa">South Africa</option>
	      <option value='zm' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag zm" data-title="Zambia">Zambia</option>
	      <option value='zr' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag zr" data-title="Zaire (former)">Zaire (former)</option>
	      <option value='zw' data-image="../public/msDropdown/images/icons/blank.gif" data-imagecss="flag zw" data-title="Zimbabwe">Zimbabwe</option>
	    </select>
     </div>
 	 </div>
 	 </s:if>
 	 
		<p></p>
			
		<s:set name="pre_seller_no" value=""/>
		<s:set name="all_price" value="0.00"/>
		
		<s:iterator var="map" value="#request.list">
			<s:set name="seller_no" value="#map.loginNo"/>
		 	<s:if test="#pre_seller_no!=#seller_no">
		 		<s:if test="#pre_seller_no!=null">
		 			<p></p>
		 		</s:if>
		 		<s:set name="pre_seller_no" value="#map.loginNo"/>
			 	<div class="seller_box">
			 		<s:property value="#seller_no"/>
			 	</div>
			 	
		 	</s:if>
		 	
		 	
		 	  <table class="data_list" >
				<tr>
					<td rowspan="3" class="title1">
						<input onclick="chkSingle(this)" type="checkbox" class="chart_css" pro_id="<s:property value="#map.product_id" />" name="product_selected"  value="<s:property value="#map.product_id"/>" checked />
						<input id="product_id_<s:property value="#map.product_id"/>" name="product_id_<s:property value="#map.product_id"/>" type="hidden"  value="<s:property value="#map.product_id" />">
						<input id="seller_id_<s:property value="#map.product_id"/>"  name="seller_id_<s:property value="#map.product_id"/>"  type="hidden"  value="<s:property value="#map.seller_id" />">
					</td>
					<!-- 产品图片 -->
					<td rowspan="2"   class="border_right">
						<img src="<s:property value="#map.product_img" />" width="100px" height="100px"/>
					</td>
					<!-- 产品名称 -->
					<td rowspan="2"   style="text-align:left;padding:10px 10px 10px 10px;overflow:hidden;">
						<s:property value="#map.product_name"/>
					</td>
					<!-- 单价 -->
					<td style="width:100px;text-align:right;padding:10px 10px 10px 10px;">
						<b>$<s:property value="#map.product_value"/></b> / <s:property value="#map.unitValueEn"/>
						<input id="product_value_<s:property value="#map.product_id"/>" name="product_value_<s:property value="#map.product_id"/>" type="hidden" value="<s:property value="#map.product_value" />">
					</td>
					<!-- 数量 -->
					<td style="width:80px;text-align:center;padding:10px 10px 10px 10px;">
						<input type="text" value="<s:property value="#map.product_count"/>" style="width:40px;" id="product_count_<s:property value="#map.product_id"/>" name="product_count_<s:property value="#map.product_id"/>"/>		
						<s:property value="#map.unitValueEn"/>
					</td>
					<!-- 总价格 -->
					<td style="width:100px;text-align:right;padding:10px 10px 10px 10px;color:#900;">
						<b>$<s:property value="#map.product_all_value"/></b>
						<s:set name="all_price" value="%{#all_price+#map.singleSumPrice}"/>
						<input class="proav" id="product_all_value_<s:property value="#map.product_id"/>" name="product_all_value_<s:property value="#map.product_id"/>" type="hidden" tst="<s:property value="#map.product_all_value"/>" value="<s:property value="#map.singleSumPrice"/>"/>
					</td>
					
					<td rowspan="3" class="tail">
						<a href="/member/rmcp.do?s=<s:property value="#map.seller_id"/>&p=<s:property value="#map.product_id"/>">Remove</a>
					</td>
					
				</tr>
				<!-- 运费 -->
				<tr>
				 
				  <td colspan="3" style="width:100px;text-align:right;padding:10px 10px 10px 10px;">
				  <div id="ship_<s:property value="#map.product_id" />">&nbsp;</div>
				  <input type="hidden" id="<s:property value="#map.product_id" />_ship" value="-" name="ship_fee"/>
				  </td>
  				
  				</tr>
  				<!-- 备注 -->
				<tr>
					<td colspan="5" class="bottom_style">
						<p><strong>Add remark to seller</strong></p>
						<textarea maxlength="2000" style="width:500px;color:#666666;" name="remark_<s:property value="#map.product_id"/>" onblur="chkValue(this)" onclick="editMark(this)">Please add remark:(e.g. color, size...)</textarea>
						<p></p>					
					</td>
				</tr>
				
			</table>
		 	
		</s:iterator>
		
		<s:if test="#productlist.size==0">
			<div class="tip_info_1">
			No Item In You Cart
			</div>
		</s:if>
		
		<s:if test="#productlist.size>0">
			
			<div align="center" style="border-left:1px solid #ddd; border-right:1px solid #ddd; border-bottom:1px solid #ddd; height:40px;">		
			
				 <table style="color:black;border:0px;" width="100%">
					<tr>
				
					<td width="10%">
						<input type="checkbox" id="product_selected_all_end" name="product_selected_all_end" checked onclick="chkAll(this)">Select All
					</td>
			
					<td width="5%"></td>
					
					<td>
						<!-- 货物总价 -->
						<input id="accounts_money" name="accounts_money" type="hidden" value=""/>
					</td>
					
					<td width="50%" style="text-align:right;">
						<div id="accounts_money_div1">
						</div>
					</td>
					
					<td width="20%">
						<div style="text-align:right;padding-right:20px;">
							<!-- 货物总价 -->
							<div id="accounts_money_div" style="height:20px;"></div>
							<!-- 运费总价 -->
							<div id="ship_fee_div" style="height:20px;"></div>
						</div>
					</td>
				
					</tr>

					<tr>
						<td colspan="5" >			
							<!-- 总价格 -->
							<div style="padding-right:10px; color:#900;font-size: 20px; font-weight: 400;text-align:right;height:30px;" id="accounts_money_all_div"></div>
						</td>
					</tr>

					<tr>
						<td colspan="5" >			
							<div style="padding-right:10px; text-align:right;">
							<input id="accounts" name="accounts" type="button" value="Payment" onclick="chkAndSubmit()">
							</div>				
						</td>
					</tr>
					
				</table>
			</div>
			
			
			<div style="text-align:right; width:100%; padding-right:20px;padding-top:10px;height:50px;">
			</div>
		
		</s:if>
		</form>
		
<script> 
$(document).ready(function(e) {	
	$("#countries").msDropdown();
});
</script>