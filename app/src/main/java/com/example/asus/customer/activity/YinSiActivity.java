package com.example.asus.customer.activity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.FileUtils;

import butterknife.Bind;
import im.delight.android.webview.AdvancedWebView;

public class YinSiActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.advanceWeb)
    TextView mWebView;
    private String strYinsi = "　一.隐私政策\n" +
            "　　本应用尊重并保护所有使用服务用户的个人隐私权。为了给您提供更准确、更有个性化的服务，本应用会按照本隐私权政策的规定使用和披露您的个人信息。但本应用将以高度的勤勉、审慎义务对待这些信息。除本隐私权政策另有规定外，在未征得您事先许可的情况下，本应用不会将这些信息对外披露或向第三方提供。本应用会不时更新本隐私权政策。 您在同意本应用服务使用协议之时，即视为您已经同意本隐私权政策全部内容。本隐私权政策属于本应用服务使用协议不可分割的一部分。\n" +
            "　　二.适用范围\n" +
            "　　1. 在您注册本应用帐号时，您根据本应用要求提供的个人注册信息；\n" +
            "　　2. 在您使用本应用网络服务，或访问本应用平台网页时，本应用自动接收并记录的您的浏览器和计算机上的信息，包括但不限于您的IP地址、浏览器的类型、使用的语言、访问日期和时间、软硬件特征信息及您需求的网页记录等数据；\n" +
            "　　3. 本应用通过合法途径从商业伙伴处取得的用户个人数据。\n" +
            "　　您了解并同意，以下信息不适用本隐私权政策：\n" +
            "　　1. 您在使用本应用平台提供的搜索服务时输入的关键字信息；\n" +
            "　　2. 本应用收集到的您在本应用发布的有关信息数据，包括但不限于参与活动、成交信息及评价详情；\n" +
            "　　3. 违反法律规定或违反本应用规则行为及本应用已对您采取的措施。\n" +
            "　　三.信息使用\n" +
            "　　1. 本应用不会向任何无关第三方提供、出售、出租、分享或交易您的个人信息，除非事先得到您的许可，或该第三方和本应用（含本应用关联公司）单独或共同为您提供服务，且在该服务结束后，其将被禁止访问包括其以前能够访问的所有这些资料。\n" +
            "　　2. 本应用亦不允许任何第三方以任何手段收集、编辑、出售或者无偿传播您的个人信息。任何本应用平台用户如从事上述活动，一经发现，本应用有权立即终止与该用户的服务协议。\n" +
            "　　3. 为服务用户的目的，本应用可能通过使用您的个人信息，向您提供您感兴趣的信息，包括但不限于向您发出产品和服务信息，或者与本应用合作伙伴共享信息以便他们向您发送有关其产品和服务的信息（后者需要您的事先同意）。\n" +
            "　　四. 信息披露\n" +
            "　　在如下情况下，本应用将依据您的个人意愿或法律的规定全部或部分的披露您的个人信息：\n" +
            "　　1. 经您事先同意，向第三方披露；\n" +
            "　　2. 为提供您所要求的产品和服务，而必须和第三方分享您的个人信息；\n" +
            "　　3. 根据法律的有关规定，或者行政或司法机构的要求，向第三方或者行政、司法机构披露；\n" +
            "　　4. 如您出现违反中国有关法律、法规或者本应用服务协议或相关规则的情况，需要向第三方披露；\n" +
            "　　5. 如您是适格的知识产权投诉人并已提起投诉，应被投诉人要求，向被投诉人披露，以便双方处理可能的权利纠纷；\n" +
            "　　6. 在本应用平台上创建的某一交易中，如交易任何一方履行或部分履行了交易义务并提出信息披露请求的，本应用有权决定向该用户提供其交易对方的联络方式等必要信息，以促成交易的完成或纠纷的解决。\n" +
            "　　7. 其它本应用根据法律、法规或者网站政策认为合适的披露。\n" +
            "　　五.信息存储和交换\n" +
            "　　本应用收集的有关您的信息和资料将保存在本应用及（或）其关联公司的服务器上，这些信息和资料可能传送至您所在国家、地区或本应用收集信息和资料所在地的境外并在境外被访问、存储和展示。\n" +
            "　　六.Cookie的使用\n" +
            "　　1. 在您未拒绝接受cookies的情况下，本应用会在您的计算机上设定或取用cookies ，以便您能登录或使用依赖于cookies的本应用平台服务或功能。本应用使用cookies可为您提供更加周到的个性化服务，包括推广服务。\n" +
            "　　2. 您有权选择接受或拒绝接受cookies。您可以通过修改浏览器设置的方式拒绝接受cookies。但如果您选择拒绝接受cookies，则您可能无法登录或使用依赖于cookies的本应用网络服务或功能。\n" +
            "　　3. 通过本应用所设cookies所取得的有关信息，将适用本政策。\n" +
            "　　信息安全\n" +
            "　　1. 本应用帐号均有安全保护功能，请妥善保管您的用户名及密码信息。本应用将通过对用户密码进行加密等安全措施确保您的信息不丢失，不被滥用和变造。尽管有前述安全措施，但同时也请您注意在信息网络上不存在“完善的安全措施”。\n" +
            "　　2. 在使用本应用网络服务进行网上交易时，您不可避免的要向交易对方或潜在的交易对\n" +
            "　　七.本隐私政策的更改\n" +
            "　　1. 如果决定更改隐私政策，我们会在本政策中、本公司网站中以及我们认为适当的位置发布这些更改，以便您了解我们如何收集、使用您的个人信息，哪些人可以访问这些信息，以及在什么情况下我们会透露这些信息。\n" +
            "　　2. 本公司保留随时修改本政策的权利，因此请经常查看。如对本政策作出重大更改，本公司会通过网站通知的形式告知。\n" +
            "　　请您妥善保护自己的个人信息，仅在必要的情形下向他人提供。如您发现自己的个人信息泄密，尤其是本应用用户名及密码发生泄露，请您立即联络本应用客服，以便本应用采取相应措施。\n";
    private  String strXieYi="《客户端使用协议》\n" +
            "\n" +
            "瑞祥客户手机客户端（以下简称“瑞祥客户APP”）是北京瑞祥佳艺建筑装饰工程有限公司提供的，在使用前，请务必认真阅读和理解《 “瑞祥客户APP”许可使用协议》（以下简称《协议》）中规定的所有权利和限制。除非您接受本《协议》条款，否则您无权下载、安装或使用本“瑞祥客户APP”及其相关服务。您一旦安装、复制、下载、访问或以其它方式使用瑞祥客户APP产品，将视为对本《协议》的接受，即表示您同意接受本《协议》各项条款的约束。如果您不同意本《协议》中的条款，请不要安装、复制或使用xxxx手机客户端软件。本《协议》是用户与瑞祥客户APP之间关于用户下载、安装、使用、复制的法律协议。\n" +
            "一、总则\n" +
            "1.1本《协议》双方为北京瑞祥佳艺建筑装饰工程有限公司与瑞祥客户APP用户，本《协议》具有合同效力。\n" +
            "1.2自瑞祥客户APP用户首次注册“瑞祥客户APP”使用账号时起，本《协议》即在北京瑞祥佳艺建筑装饰工程有限公司和瑞祥客户APP用户之间产生法律效力。\n" +
            "1.3北京瑞祥佳艺建筑装饰工程有限公司有权修改或重新制定本《协议》，并通过温特斯网站或瑞祥佳艺网站以公告形式对瑞祥客户APP用户进行告知。瑞祥客户APP用户在公告之日后使用“瑞祥客户APP”的行为视为接受北京瑞祥佳艺建筑装饰工程有限公司对《协议》的修改或重新制定。   \n" +
            "二、知识产权声明\n" +
            "2.1本“瑞祥客户APP”的一切版权、商标权、专利权、商业秘密等知识产权，均受中华人民共和国著作权法、商标法、专利法、反不正当竞争法和相应的国际条约以及其他知识产权法律法规的保护，除涉及第三方授权的软件或技术外，北京瑞祥佳艺建筑装饰工程有限公司享有上述知识产权。\n" +
            "2.2未经北京瑞祥佳艺建筑装饰工程有限公司书面同意，瑞祥客户APP用户不得为任何营利性或非营利性的目的利用、转让“瑞祥客户APP”使用权。\n" +
            "2.3未经北京瑞祥佳艺建筑装饰工程有限公司书面同意，瑞祥客户APP用户不得自行或委托他人对“北京瑞祥佳艺建筑装饰工程有限公司”进行反项工程、反向编译或反汇编。\n" +
            "\n" +
            "三、用户使用须知\n" +
            "3.1瑞祥客户APP由北京瑞祥佳艺建筑装饰工程有限公司提供产品支持。\n" +
            "3.2瑞祥客户APP功能：瑞祥客户手机客户端是对基于Android（安卓）系统的手机、平板电脑(PAD)等设备（以下简称手机）进行数据加密及隐私保护管理的工具，包含安全邮件的收发、浏览、管理和加密存储；安全通信录、私密短信的管理、导入、导出；安全浏览器的浏览、下载、书签管理和下载数据的加密存储；安全图片、安全视频、安全文件柜的浏览、管理、导入、导出和加密存储等功能。\n" +
            "3.3瑞祥客户APP的修改和升级：瑞祥客户APP保留随时为用户提供本软件的修改、升级版本的权利。由用户选择确定后，软件会进行升级更新，产生相应的数据流量费，由运营商收取。\n" +
            "3.4瑞祥客户APP不含有任何旨在破坏用户计算机数据和获取用户隐私信息的恶意代码，不含有任何跟踪、监视用户计算机的功能代码，不会监控用户网上、网下的行为，不会收集用户使用其它软件、文档等个人信息，不会泄漏用户隐私。\n" +
            "3.5  用户应在遵守法律及本协议的前提下使用瑞祥客户APP。用户无权实施包括但不限于下列行为：\n" +
            "3.5.1不得删除或者改变瑞祥客户APP上的所有权利管理电子信息；\n" +
            "3.5.2不得故意避开或者破坏著作权人为保护瑞祥客户APP著作权而采取的技术措施；\n" +
            "3.5.3 用户不得利用瑞祥客户APP误导、欺骗他人；\n" +
            "3.5.4违反国家规定，对计算机信息系统功能进行删除、修改、增加、干扰，造成计算机信息系统不能正常运行；\n" +
            "3.5.5未经允许，进入计算机信息网络或者使用计算机信息网络资源；\n" +
            "3.5.6未经允许，对计算机信息网络功能进行删除、修改或者增加的；\n" +
            "3.5.7未经允许，对计算机信息网络中存储、处理或者传输的数据和应用程序进行删除、修改或者增加；\n" +
            "3.5.8 破坏瑞祥客户APP系统或网站的正常运行，故意传播计算机病毒等破坏性程序；\n" +
            "3.5.9 其他任何危害计算机信息网络安全的。\n" +
            "\n" +
            "四、免责与责任限制\n" +
            "4.1瑞祥客户APP经过详细的测试，但不能保证与所有的软硬件系统完全兼容，不能保证瑞祥客户APP完全没有错误。如果出现不兼容及软件错误的情况，用户可拨打技术支持电话将情况报告瑞祥客户APP客服，获得技术支持。如果无法解决兼容性问题，用户可以退订瑞祥客户APP，删除瑞祥客户APP客户端软件。\n" +
            "4.2在适用法律允许的最大范围内，对因使用或不能使用瑞祥客户APP所产生的损害及风险，包括但不限于直接或间接的个人损害、商业赢利的丧失、贸易中断、商业信息的丢失或任何其它经济损失，北京瑞祥佳艺建筑装饰工程有限公司不承担任何责任。\n" +
            "4.3对于因瑞祥客户APP系统或互联网网络故障、计算机故障或病毒、信息损坏或丢失、计算机系统问题或其它任何不可抗力原因而产生损失，北京瑞祥佳艺建筑装饰工程有限公司不承担任何责任。\n" +
            "五、 法律及争议解决\n" +
            "5.1本协议适用中华人民共和国法律。\n" +
            "5.2因本协议引起的或与本协议有关的任何争议，各方应友好协商解决；协商不成的，任何一方均可将有关争议提交至北京仲裁委员会并按照其届时有效的仲裁规则仲裁；仲裁裁决是终局的，对各方均有约束力。\n" +
            "六、其他条款\n" +
            "6.1如果本协议中的任何条款无论因何种原因完全或部分无效或不具有执行力，或违反任何适用的法律，则该条款被视为删除，但本协议的其余条款仍应有效并且有约束力。 \n" +
            "6.2北京瑞祥佳艺建筑装饰工程有限公司在法律允许范围内对本协议拥有解释权与修改权。\n" +
            "\n" +
            "xxxxxxxx";
    @Override
    public int getLayout() {
        return R.layout.activity_yin_si;
    }

    @Override
    public void initData() {
        String type = getIntent().getStringExtra("type");
        if (type.equals("协议")) {
            tvTitle.setText("用户协议");
            mWebView.setText(strXieYi);
        }else{
            tvTitle.setText("隐私政策");
            mWebView.setText(strYinsi);
        }
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }
}
